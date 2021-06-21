import { useRoute, useRouter } from "vue-router";
import { getCurrentInstance, unref } from "vue";
import { useStore } from "vuex";
import dayjs from "dayjs";

class Deffer {
  promise: any = null;
  resolve: any = null;
  reject: any = null;
  constructor() {
    this.promise = new Promise((resolve, reject) => {
      this.resolve = resolve;
      this.reject = reject;
    });
  }
}

let debounceTimer: any = null;

export const useWebCommonSetting = () => {
  const ctx = getCurrentInstance();
  const router = useRouter();
  const route = useRoute();
  const store = useStore();
  /**
   * 获取全局路由对象
   * @returns
   */
  const getRouter = () => {
    return router;
  };
  /**
   * 获取当前路由对象
   * @returns
   */
  const getRoute = () => {
    return route;
  };
  /**
   *
   * @param path 路由跳转
   */
  const getRouterLinkTo = (path: string) => {
    if (!path) router.push("/");
    router.push(path);
  };
  /**
   * 返回
   */
  const goBack = () => {
    router.back();
  };

  // 全局消息提示封装
  const getGlobalMessage = (
    type: string = "success" || "error" || "warning",
    message: string
  ) => {
    ctx?.appContext.config.globalProperties.$message[type](message);
  };
  /**
   * confirm
   */
  const getDialogConfirm = ctx?.appContext.config.globalProperties.$confirm;
  /**
   * 预览图
   */
  const onImagePreview = ctx?.appContext.config.globalProperties.$preview;
  /**
   * 时间格式化
   */
  const getTimeStampFormat = (time: number, format = "YYYY-MM-DD hh:mm:ss") => {
    if (!time) {
      return "-- --";
    }
    return dayjs(time).format(format);
  };
  /**
   * form表单重置
   * @param formRef
   * @returns
   */
  const getFormReset = (formRef) => {
    const form = unref(formRef);
    if (!form) {
      return;
    }
    (form as any).resetFields();
  };
  /**
   * 暴露全局环境变量
   * @returns
   */
  const useGlobalEnv = () => {
    const env = import.meta.env.MODE;
    return { env };
  };
  /**
   * 格式化尺寸
   */
  const formatSize = (val, unit = "px") => {
    let result = "100%";
    let type = Object.prototype.toString.call(val);
    switch (type) {
      case "[object Number]":
        result = val + unit;
        break;
      case "[object String]":
        // 是否百分比
        result = val.includes("%") ? val : val + "px";
        break;
    }
    return result;
  };
  /**
   * 设置loading
   * @param value
   */
  const setGlobalLoading = (value: boolean) => {
    store.dispatch("setLoading", value);
  };
  /**
   * 获取loading
   * @returns
   */
  const getGlobalLoading = () => {
    return store.state.onLoading;
  };
  /**
   * 防抖函数
   *  -- 在一段时间内多次触发函数fn 当间隔一段时间没有再次触发fn后才会执行
   * @param fn
   * @param await
   */
  const debounce = (fn: Function, timeout = 800) => {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(fn, timeout);
  };
  /**
   * 节流函数
   *  --在一段时间内多次触发函数fn 一段时间内只会执行一次
   * @param fn
   * @param timeout
   * @returns
   */
  const throttle = (fn: Function, timeout = 800) => {
    if (debounceTimer) {
      return;
    }
    debounceTimer = setTimeout(() => {
      fn();
      debounceTimer = null;
    }, timeout);
  };
  /**
   * 获取随机数
   * @param number
   * @returns
   */
  const getRandomNum = (number: number) => {
    return Math.floor(Math.random() * number);
  };

  return {
    getRouter,
    getRoute,
    Deffer,
    getRouterLinkTo,
    getGlobalMessage,
    getTimeStampFormat,
    getDialogConfirm,
    getFormReset,
    useGlobalEnv,
    formatSize,
    goBack,
    onImagePreview,
    getGlobalLoading,
    setGlobalLoading,
    debounce,
    throttle,
    getRandomNum,
  };
};
