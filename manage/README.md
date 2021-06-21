# vite
    中文文档：https://cn.vitejs.dev/
    依赖：vite需要nodejs版本大于等于12.0.0；打包使用的是rollup
    脚手架创建项目：npm init @vitejs/app first-vite-app --template vue
    构建优化：
        css代码分割：Vite 会自动地将一个异步 chunk 模块中使用到的 CSS 代码抽取出来并为其生成一个单独的文件。这个 CSS 文件将在该异步 chunk 加载完成时自动通过一个 &lt;link&gt; 标签载入，该异步 chunk 会保证只在 CSS 加载完毕后再执行，避免发生 <a href="https://en.wikipedia.org/wiki/Flash_of_unstyled_content#:~:text=A%20flash%20of%20unstyled%20content,before%20all%20information%20is%20retrieved." target="_blank" style="font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; box-sizing: border-box; touch-action: manipulation; text-decoration-line: none; color: var(--c-brand);">FOUC 。
如果你更倾向于将所有的 CSS 抽取到一个文件中，你可以通过设置 <a href="https://cn.vitejs.dev/config/#build-csscodesplit" style="box-sizing: border-box; touch-action: manipulation; text-decoration-line: none; color: var(--c-brand);">build.cssCodeSplit 为 false 来禁用 CSS 代码分割。
    环境变量：创建.env.xxx来标识环境 然后构建命令："test": "vue-tsc --noEmit &amp;&amp; vite build --mode test",   ；然后使用import.meta.env.MODE

# element-plus使用
```
npm i --save element-plus
```
```
babel.config.js
module.exports = {
    plugins: [
        [
            "import",
            {
                libraryName: 'element-plus',
                customStyleName: (name) => {
                    name = name.slice(3)
                    return `element-plus/packages/theme-chalk/src/${name}.less`;
                },
            },
        ],
    ],
};
```
```
使用：
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
  ElPagination,
  ElRow,
  ElSelect,
  ElTable,
  ElTableColumn,
  ElMessage,
  ElMessageBox,
  ElCol,
  ElCollapse
} from "element-plus";

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
  ElCollapse
];

const plugins = [ElMessage, ElMessageBox];

import { App } from "vue";

const registerAntd = {
  install: function (app: App<Element>) {
    components.forEach((component) => {
      app.component(component.name, component);
    });
    plugins.forEach((plugin) => {
      app.use(plugin);
    });
  },
};

export default registerAntd;
```
### 坑1---打包失败
```
Cannot find name 'ComponentSize'.
Cannot find name 'Nullable'.

在eleemnt-plus的github issues中找到解决办法
构建命令修改：
"build": "vue-tsc --noEmit --skipLibCheck && vite build --mode production",
```


# vue-router
```
npm install vue-router@4.0
```
```
export default createRouter({
  history: createWebHashHistory(),
  routes,
});
在组件中使用router
//引入路由函数
import { useRouter } from "vue-router";

//使用
setup() {
    //初始化路由
    const router = useRouter();
    router.push({
        path: "/"
    });
    
    return {};
}
```

# vuex
```
npm install vuex@next --save
  /**
     * 计算属性
     */
    const store = useStore();
    const name = computed(() => {
      return store.state.name;
    });
    return { name };
```

# getCurrentInstance
vue3.0中使用getCurrentInstance来获取当前vue实例

# 组件间通信
```
 props: {
    child: Number,
  },
  setup(props, { emit }) {
    const newChild = computed(() =&gt; {
      return props.child;
    });
    const increment = () =&gt; {
      emit("increment");
    };
    return {
      newChild,
      increment,
    };
  },
```


# less
```
npm install less less-loader --dev
```


# vite.config.js
```
/**
 * 有不知道配置怎么写的可以看源码 具体配置项
 */
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

const path = require("path");

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  /**
   * 配置别名
   */
  alias: {
    "@": path.resolve(__dirname, "src"),
  },
  /**
   * 静态资源目录，开发模式下会自动放到 / 下，生产模式下会自动放到 outDir 根路径下。
   * 该目录可以配置为文件系统绝对目录或者相对于项目根目录的相对路径
   */
  publicDir: "public",
  server: {
    port: 9527,
    hmr: true,
    //  反向代理
    proxy: {
      "/api": {
        target: "http://localhost:9999/blogManage",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});

```


# 富文本编辑器(https://ckang1229.gitee.io/vue-markdown-editor/zh/senior/upload-image.html)
```
npm i @kangc/ @next -S
```
```


import { creatApp } from 'vue';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// 引入你所使用的主题 此处以 github 主题为例
import githubTheme from '@kangc/v-md-editor/lib/theme/github';
import '@kangc/v-md-editor/lib/theme/style/github.css';
 
VMdPreview.use(githubTheme);
 
const app = creatApp(/*...*/);
 
app.use(VMdPreview);
```

