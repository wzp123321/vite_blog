import { App } from "vue";

/**
 * 注册loading指令
 * @param app
 */
export const onLoadingDirectiveRegister = (app: App<Element>) => {
  app.directive("loading", {
    /**
     * 初始化
     * @param el 绑定的元素
     */
    mounted(el, binding) {
      console.log("binding--------", binding);
      const domEle = document.body.querySelector(".c-Loading-components");
      if (!domEle) {
        const divEle = document.createElement("div");
        divEle.className = "sk-chase";
        for (let i = 0; i < 6; i++) {
          const childEle = document.createElement("div");
          childEle.className = "sk-chase-dot";
          divEle.appendChild(childEle);
        }

        const containerEle = document.createElement("div");
        containerEle.className =
          "c-Loading-components flex-column-center-center";
        containerEle.appendChild(divEle);
        containerEle.style.visibility = "hidden";
        el.appendChild(containerEle);
      }
    },
    /**
     * 绑定的数据更新
     * @param el
     * @param binding
     */
    updated(el, binding) {
      console.log(el);
      const flag = binding.value;
      const domEle = document.body.querySelector(".c-Loading-components");
      (domEle as any).style.visibility = flag ? "visible" : "hidden";
    },
  });
};
