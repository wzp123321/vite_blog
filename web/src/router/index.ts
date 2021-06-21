import { createRouter, RouteRecordRaw, createWebHashHistory } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "App",
    component: () => import("../views/Home.vue"),
    children: [
      {
        path: "/",
        name: "App",
        component: () => import("../pages/main/index.vue"),
      },
      {
        path: "/article",
        name: "Article",
        component: () => import("../pages/article/index.vue"),
      },
    ],
  },
];

export default createRouter({
  routes,
  history: createWebHashHistory(),
});
