import { reactive, toRefs } from "vue";

export const useCommonDialog = () => {
  const dialogState = reactive({
    visible: false,
  });

  /**
   * 开 & 关闭
   */
  const operation = (value = true) => {
    dialogState.visible = value;
  };

  return { ...toRefs(dialogState), operation };
};
