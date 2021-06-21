import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";

export const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "App",
    meta: {
      name: "首页",
      hasSlide: true,
    },
    component: () =>
      /* webpackChunkName: 'blog-manage-home' */ import("@/views/Home.vue"),
    redirect: "/adminManage",
    children: [
      {
        path: "/adminManage",
        name: "AdminManage",
        meta: {
          name: "管理员管理",
          icon: "iconguanliyuan",
          isSlide: true,
        },
        component: () =>
          /* webpackChunkName: 'admin-manage' */ import(
            "@/pages/admin-manage/index.vue"
          ),
      },
      {
        path: "/enumManage",
        name: "EnumManage",
        meta: {
          name: "枚举管理",
          icon: "icondictionary",
          isSlide: true,
        },
        component: () =>
          /* webpackChunkName: 'enum-manage' */ import(
            "@/pages/enum-manage/index.vue"
          ),
      },
      {
        path: "/dicManage",
        name: "DictionaryManage",
        meta: {
          name: "字典管理",
          icon: "iconzidianguanli",
          isSlide: true,
        },
        component: () =>
          /* webpackChunkName: 'dictionary-manage' */ import(
            "@/pages/dictionary-manage/index.vue"
          ),
      },
      {
        path: "/articleManage",
        name: "ArticleManage",
        meta: {
          name: "文章管理",
          icon: "icon16",
          isSlide: true,
        },
        redirect: "/articleManage/list",
        component: () =>
          /* webpackChunkName: 'article-manage' */ import(
            "@/pages/article-manage/index.vue"
          ),
        children: [
          {
            path: "list",
            name: "ArticleList",
            meta: {
              name: "文章列表",
            },
            component: () =>
              /* webpackChunkName: 'article-manage-list' */ import(
                "@/pages/article-manage/list/index.vue"
              ),
          },
          {
            path: "create",
            name: "ArticleCreate",
            meta: {
              name: "新增文章",
            },
            component: () =>
              /* webpackChunkName: 'article-manage-add' */ import(
                "@/pages/article-manage/create/index.vue"
              ),
          },
          {
            path: "update",
            name: "ArticleUpdate",
            meta: {
              name: "编辑文章",
            },
            component: () =>
              /* webpackChunkName: 'article-manage-update' */ import(
                "@/pages/article-manage/create/index.vue"
              ),
          },
          {
            path: "preview",
            name: "ArticlePreview",
            meta: {
              name: "文章预览",
            },
            component: () =>
              /* webpackChunkName: 'article-manage-preview' */ import(
                "@/pages/article-manage/preview/index.vue"
              ),
          },
        ],
      },
      {
        path: "/bannerManage",
        name: "BannerManage",
        meta: {
          name: "Banner管理",
          icon: "iconbanner",
          isSlide: true,
        },
        component: () =>
          /* webpackChunkName: 'banner-manage' */ import(
            "@/pages/banner-manage/index.vue"
          ),
        redirect: "/bannerManage/list",
        children: [
          {
            path: "list",
            name: "BannerManageList",
            meta: {
              name: "Banner列表",
            },
            component: () =>
              /* webpackChunkName: 'banner-manage-list' */ import(
                "@/pages/banner-manage/list/index.vue"
              ),
          },
          {
            path: "create",
            name: "BannerManageCreate",
            meta: {
              name: "新增Banner",
            },
            component: () =>
              /* webpackChunkName: 'banner-manage-create' */ import(
                "@/pages/banner-manage/create/index.vue"
              ),
          },
          {
            path: "update",
            name: "BannerManageUpdate",
            meta: {
              name: "编辑Banner",
            },
            component: () =>
              /* webpackChunkName: 'banner-manage-update' */ import(
                "@/pages/banner-manage/create/index.vue"
              ),
          },
        ],
      },
    ],
  },
  {
    path: "/signin",
    name: "Signin",
    meta: {
      noCheck: true,
      hasSlide: false,
    },
    component: () =>
      /* webpackChunkName:'blog-manage-signin' */ import(
        "@/views/ui/Signin.vue"
      ),
  },
];

export default createRouter({
  history: createWebHashHistory(),
  routes,
});
