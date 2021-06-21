import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";
import store from "./store";

/**
 * 引入公共样式
 */
import "./assets/less/common.less";
import 'element-plus/lib/theme-chalk/index.css'

// 引入组件库
import ElementPlusRegister from "./plugins/ElementPlusRegister";

const app = createApp(App);
ElementPlusRegister(app);
app.use(router);
app.use(store);
app.mount("#app");
