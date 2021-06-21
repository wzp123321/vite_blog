import { reactive, toRefs } from "vue";
import Service from "../../service/package/index";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

interface AdminState {
  loading: boolean;
  enumList: TableListManage.EnumVoInfo[];
}

export const useEnumOperationController = () => {
  const { getGlobalMessage } = useWebCommonSetting();

  const adminState: AdminState = reactive({
    loading: false,
    enumList: [],
  });

  /**
   * 查询列表
   */
  const getEnumList = async () => {
    adminState.loading = true;
    try {
      const res = await Service.EnumService.getEnumVoList({
        page: 1,
      });
      if (res && res.code == 200 && res.data.list) {
        adminState.enumList = res.data.list;
        adminState.loading = false;
        if (adminState.enumList.length === 0 && res.data.total > 9) {
          getEnumList();
        }
      } else {
        getGlobalMessage("error", "请求失败！");
        adminState.loading = false;
      }
    } catch (error) {
      getGlobalMessage("error", "请求失败！");
      adminState.loading = false;
    }
  };

  return {
    ...toRefs(adminState),
    getEnumList,
  };
};
