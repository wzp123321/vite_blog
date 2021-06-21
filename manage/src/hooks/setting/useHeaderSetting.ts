import { useStore } from "vuex";
import { computed, getCurrentInstance } from "vue";
import { useWebCommonSetting } from "../common/useWebCommonSetting";

export const useHeaderSetting = () => {
  const ctx = getCurrentInstance();
  const { getRouterLinkTo, getGlobalMessage } = useWebCommonSetting();

  const appStore = useStore();
  //获取用户名
  const getHeaderUserName = computed(() => appStore.state.userName);

  const getUserLoginOut = () => {
    ctx?.appContext.config.globalProperties.$confirm(
      "你确认退出登录吗？",
      "退出登录",
      {
        type: "warning",
        confirmButtonText: "确定",
        showClose: false,
        callback: (action: string) => {
          if (action === "confirm") {
            getRouterLinkTo("/signin");
            window.localStorage.removeItem("mulit_token");
            getGlobalMessage("success", "退出登录");
          }
        },
      }
    );
  };

  return { getHeaderUserName, getUserLoginOut };
};
