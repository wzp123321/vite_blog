import { reactive, toRefs } from "@vue/reactivity";
import Service from "../../../service/package/index";

import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";

interface BannerState {
  bannerList: TableListManage.BannerVoInfo[];
  pagination: CommonModule.PaginationParams;
}

export const useBannerListController = () => {
  const bannerState = reactive<BannerState>({
    bannerList: [],
    pagination: {
      page: 1,
    },
  });
  const { getGlobalMessage, getDialogConfirm, setGlobalLoading } =
    useWebCommonSetting();

  /**
   * 请求列表
   */
  const getBannerList = async () => {
    const { page } = bannerState.pagination;
    try {
      setGlobalLoading(true);
      const res = await Service.BannerService.getBannerList({
        page,
      });
      if (res && res.code == 200 && res.data && res.message == "SUCCESS") {
        bannerState.bannerList = res.data.list;
        bannerState.pagination.total = res.data.total;
        setGlobalLoading(false);
      } else {
        getGlobalMessage("error", "请求失败！");
        setGlobalLoading(false);
      }
    } catch (error) {
      setGlobalLoading(false);
      getGlobalMessage("error", "请求失败！");
    }
  };
  /**
   * table change
   */
  const onTableChange = (value: number) => {
    bannerState.pagination.page = value;
    getBannerList();
  };
  /**
   * 删除banner
   */
  const getBannerDelete = (id: number) => {
    getDialogConfirm("你确认删除Banner吗？", "删除Banner", {
      cancelButtonText: "取消",
      confirmButtonText: "确认",
      callback: async function (action: string, instance: string) {
        if (action === "confirm") {
          console.log(instance);
          try {
            const res = await Service.BannerService.getBannerDelete({ id });
            if (res && res.code == 200 && res.message == "SUCCESS") {
              getGlobalMessage("success", "删除成功！");
              getBannerList();
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
    ...toRefs(bannerState),
    getBannerList,
    onTableChange,
    getBannerDelete,
  };
};
