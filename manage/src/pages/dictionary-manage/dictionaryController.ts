import { reactive, toRefs } from "@vue/reactivity";
import Service from "../../service/package/index";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

interface DictionaryState {
  type: undefined | number;
  pagination: CommonModule.PaginationParams;
  dictionaryList: TableListManage.DictionaryVoInfo[];
}

export const dictionaryController = () => {
  const { getGlobalMessage, Deffer, getDialogConfirm, setGlobalLoading } =
    useWebCommonSetting();

  let deffer;

  const rules = {
    type: [
      {
        required: true,
        message: "请选择字典类型",
        trigger: "change",
      },
    ],
    title: [
      {
        required: true,
        message: "请输入字典标题",
        trigger: "blur",
      },
    ],
  };
  let dictionaryState = reactive<DictionaryState>({
    type: undefined,
    pagination: {
      page: 1,
      total: 0,
    },
    dictionaryList: [],
  });
  /**
   * 分页查询
   * @param value 页码
   */
  const onPaginationChange = (value: number) => {
    dictionaryState.pagination.page = value;
    getDictionaryListQuery();
  };
  /**
   * 查询字典列表公共方法
   */
  const getDicList = async (): Promise<{
    list: TableListManage.DictionaryVoInfo[];
    total: number;
  }> => {
    deffer = new Deffer();
    setGlobalLoading(true);
    const { type } = dictionaryState;
    const { page } = dictionaryState.pagination;
    let params = { page, limit: 10 };
    if (Object.prototype.toString.call(type) === "[object Number]") {
      Object.assign(params, { type });
    }
    try {
      const res = await Service.DictionaryService.getDictionaryList(params);

      if (res && res.code == 200 && res.message == "SUCCESS") {
        deffer.resolve(res.data);
        setGlobalLoading(false);
      } else {
        setGlobalLoading(false);
        deffer.resolve({
          list: [],
          total: 0,
        });
        getGlobalMessage("error", "查询列表失败！");
      }
    } catch (error) {
      deffer.reject({
        list: [],
        total: 0,
      });
      setGlobalLoading(false);
      getGlobalMessage("error", "查询列表失败！");
    }
    return deffer.promise;
  };
  /**
   * 查询具体类别的字典
   */
  const getDictionaryListQuery = async () => {
    try {
      const res = await getDicList();
      if (res) {
        dictionaryState.dictionaryList = res.list;
        dictionaryState.pagination.total = res.total;
      }
    } catch (error) {
      console.log(error);
    }
  };
  /**
   * 获取一级目录列表
   */
  const getFirstCatalogList = async (value: number) => {
    deffer = new Deffer();
    dictionaryState.type = value;
    try {
      const res = await getDicList();
      deffer.resolve(res.list);
    } catch (error) {
      console.log(error);
    }
    return deffer.promise;
  };
  /**
   * 删除字典
   */
  const getDicDelete = (id: number) => {
    getDialogConfirm("删除字典", "你确认删除该字典吗？", {
      cancelButtonText: "取消",
      confirmButtonText: "确认",
      callback: async function (action: string) {
        if (action === "confirm") {
          try {
            const res = await Service.DictionaryService.getDictionaryDelete({
              id,
            });
            if (res && res.code == 200 && res.message == "SUCCESS") {
              getGlobalMessage("success", "删除成功！");
              getDictionaryListQuery();
            } else {
              getGlobalMessage("error", "删除失败！");
            }
          } catch (error) {
            getGlobalMessage("error", "删除失败！");
          }
        }
      },
    });
  };
  return {
    ...toRefs(dictionaryState),
    getDictionaryListQuery,
    rules,
    getDicDelete,
    onPaginationChange,
    getFirstCatalogList,
  };
};
