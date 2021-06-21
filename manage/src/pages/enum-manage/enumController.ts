import { reactive, toRefs } from "vue";
import Service from "../../service/package/index";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

export const useEnumOperationController = () => {
  const { getGlobalMessage, Deffer, setGlobalLoading } = useWebCommonSetting();
  const enumState = reactive({
    deffer: new Deffer(),
  });

  /**
   * 查询列表
   */
  const getEnumList = async (): Promise<TableListManage.EnumVoInfo[]> => {
    enumState.deffer = new Deffer();
    setGlobalLoading(true);
    try {
      const res = await Service.EnumService.getEnumVoList({
        page: 1,
      });
      if (res && res.code == 200 && res.data.list) {
        enumState.deffer.resolve(res.data.list);
        setGlobalLoading(false);
        if (res.data.list.length === 0 && res.data.total > 9) {
          getEnumList();
        }
      } else {
        enumState.deffer.resolve([]);
        getGlobalMessage("error", "请求失败！");
        setGlobalLoading(false);
      }
    } catch (error) {
      console.log(error);
      enumState.deffer.reject([]);
      getGlobalMessage("error", "请求失败！");
      setGlobalLoading(false);
    }
    return enumState.deffer.promise;
  };

  return {
    ...toRefs(enumState),
    getEnumList,
  };
};
