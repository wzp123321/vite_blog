import {
  ElAside,
  ElButton,
  ElContainer,
  ElDialog,
  ElDivider,
  ElDrawer,
  ElForm,
  ElFormItem,
  ElHeader,
  ElIcon,
  ElInput,
  ElMain,
  ElMenu,
  ElMenuItem,
  ElRow,
  ElSelect,
  ElTable,
  ElTableColumn,
  ElMessage,
  ElMessageBox,
  ElCol,
  ElCollapse,
  ElPagination,
  ElBreadcrumb,
  ElBreadcrumbItem,
  ElOption,
  ElUpload,
  ElSteps,
  ElStep,
  ElSwitch,
  ElTag,
} from "element-plus";

/**
 * markdown编辑器
 */
import VMdEditor from "@kangc/v-md-editor";
import githubTheme from "@kangc/v-md-editor/lib/theme/github.js";

/**
 * 图片预览插件
 */
import ImagePreview from "./VPreview/index";

const components = [
  ElAside,
  ElButton,
  ElContainer,
  ElDialog,
  ElDivider,
  ElDrawer,
  ElForm,
  ElFormItem,
  ElHeader,
  ElIcon,
  ElInput,
  ElMain,
  ElMenu,
  ElMenuItem,
  ElPagination,
  ElRow,
  ElSelect,
  ElTable,
  ElTableColumn,
  ElMessageBox,
  ElCol,
  ElCollapse,
  ElBreadcrumb,
  ElBreadcrumbItem,
  ElOption,
  ElUpload,
  ElSteps,
  ElStep,
  ElSwitch,
  ElTag
];

const plugins = [ElMessage, ElMessageBox, ImagePreview];

import { App } from "vue";

const registerAntd = {
  install: function (app: App<Element>) {
    components.forEach((component) => {
      app.component(component.name, component);
    });
    plugins.forEach((plugin) => {
      app.use(plugin);
    });
    VMdEditor.use(githubTheme);
    app.use(VMdEditor);
  },
};

export default registerAntd;
