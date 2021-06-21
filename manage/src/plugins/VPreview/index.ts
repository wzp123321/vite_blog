import { render, createVNode, App } from "vue";
import ImagePreviewConstructor from "./index.vue";
const body = document.body;

/**
 * 自定义插件
 * @param options
 */
const Preview = function (options: { url: String }) {
  const modelDom = body.querySelector(".image-preview");
  if (modelDom) {
    body.removeChild(modelDom);
  }
  const container = document.createElement("div");
  container.className = `image-preview`;
  //创建虚拟节点
  const vm = createVNode(ImagePreviewConstructor, options);
  //渲染虚拟节点
  render(vm, container);
  document.body.appendChild(container);
};

export default {
  install: function (app: App<Element>) {
    app.config.globalProperties.$preview = Preview;
  },
};
