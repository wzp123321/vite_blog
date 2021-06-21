import { reactive, toRefs } from "vue";
import Service from "../../service/package/index";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

interface AdminState {
  adminList: TableListManage.AdminVoInfo[];
  pagination: CommonModule.PaginationParams;
}
/**
 * 查询列表模块
 * @returns 列表 分页 loading list请求
 */
export const useAdminOperationController = () => {
  const { getGlobalMessage, getDialogConfirm, setGlobalLoading } =
    useWebCommonSetting();

  const adminState = reactive<AdminState>({
    adminList: [],
    pagination: {
      page: 1,
      total: 0,
    },
  });

  const paginationChange = (value: number) => {
    adminState.pagination.page = value;
    getAdminList();
  };
  /**
   * 查询列表
   */
  const getAdminList = async () => {
    const { page } = adminState.pagination;
    setGlobalLoading(true);
    try {
      const res = await Service.AdminService.getAdminVoList({
        page,
      });
      if (res && res.code == 200 && res.data.list) {
        adminState.adminList = res.data.list;
        adminState.pagination.total = res.data.total;
        setGlobalLoading(false);
        if (adminState.adminList.length === 0 && res.data.total > 9) {
          getAdminList();
        }
      } else {
        getGlobalMessage("error", "请求失败！");
        setGlobalLoading(false);
      }
    } catch (error) {
      getGlobalMessage("error", "请求失败！");
      setGlobalLoading(false);
    }
  };

  /**
   * 删除管理员
   * @param name 管理员名
   * @param fn 回调函数
   */
  const getAdminDelete = (name: string) => {
    getDialogConfirm("你确认删除该管理员吗？", "删除管理员", {
      cancelButtonText: "取消",
      confirmButtonText: "确认",
      callback: async function (action: string, instance: string) {
        if (action === "confirm") {
          console.log(instance);
          try {
            const res = await Service.AdminService.getAdminVoDelete({ name });
            if (res && res.code == 200 && res.message == "SUCCESS") {
              getGlobalMessage("success", "删除成功！");
              getAdminList();
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
    ...toRefs(adminState),
    getAdminList,
    getAdminDelete,
    paginationChange,
  };
};
