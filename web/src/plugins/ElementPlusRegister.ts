import { App } from "vue";
import { NButton } from "naive-ui";

const components = [ElMenuItem, ElMenu, ElSubmenu];

/**
 * 注册elemen-plus组件
 * @param app
 */
const ElementPlusRegister = (app: App<Element>) => {
  // 注册组件
  components.forEach((componentItem) => {
    app.component(componentItem.name, componentItem);
  });
};

export default ElementPlusRegister;
