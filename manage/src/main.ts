import { createApp } from "vue";
import App from "./App.vue";
import "@/assets/less/common.less";
// editor css
import "@kangc/v-md-editor/lib/style/base-editor.css";
import "@kangc/v-md-editor/lib/theme/style/github.css";
import "highlight.js/styles/atom-one-dark.css";
// router
import router from "./routers";
import store from "./store";
import Service from "./service/package/index";

router.beforeEach(async (to, from, next) => {
  console.log("from-", from);
  const isLogined = store.state.isLogined;
  if (to && to.meta && to.meta.noCheck) {
    next();
  } else {
    if (isLogined) {
      next();
    } else {
      const res = await Service.SigninService.userLoginStatusVerify({});
      if (res && res.message == "SUCCESS") {
        store.dispatch(
          "setUserLoginInfo",
          Object.assign(res.data, { isLogined: true })
        );
        next();
      } else {
        next("/signin");
      }
    }
  }
});

const app = createApp(App);
/**
 * 注册antd
 */
import registerAntd from "./plugins/registerAntdComp";
/**
 * 注册全局loading指令
 */
import { onLoadingDirectiveRegister } from "./directive/index";
onLoadingDirectiveRegister(app);

app.use(registerAntd);
app.use(router);
app.use(store);
app.mount("#app");
