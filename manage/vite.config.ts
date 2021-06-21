/**
 * 有不知道配置怎么写的可以看源码 具体配置项
 */
import { defineConfig } from "vite";
/**
 * 引入插件
 */
import { createVitePlugins } from "./src/plugins/packagePlugins/index";
import serviceConfig from "./src/config/constant";
const { resolve } = require("path");

// https://vitejs.dev/config/
export default ({ mode }) => {
  console.log("mode,process.env.NODE_ENV", mode, process.env.NODE_ENV);

  return defineConfig({
    plugins: createVitePlugins(),
    resolve: {
      /**
       * 配置别名
       */
      alias: {
        "@": resolve(__dirname, "./src"),
        "@/assets": resolve(__dirname, "./src/assets"),
        "@/components": resolve(__dirname, "./src/components"),
        "@/config": resolve(__dirname, "./src/config"),
        "@/hooks": resolve(__dirname, "./src/hooks"),
        "@/pages": resolve(__dirname, "./src/pages"),
        "@/plugins": resolve(__dirname, "./src/plugins"),
        "@/service": resolve(__dirname, "./src/service"),
        "@/views": resolve(__dirname, "./src/views"),
        // 解决vue-i18n警告You are running the esm-bundler build of vue-i18n. It is recommended to configure your bundler to explicitly replace feature flag globals with boolean literals to get proper tree-shaking in the final bundle.
        "vue-i18n": "vue-i18n/dist/vue-i18n.cjs.js",
      },
    },
    /**
     * 静态资源目录，开发模式下会自动放到 / 下，生产模式下会自动放到 outDir 根路径下。
     * 该目录可以配置为文件系统绝对目录或者相对于项目根目录的相对路径
     */
    publicDir: serviceConfig.PUBLIC_DIR,
    /**
     * server
     */
    server: {
      port: 9527,
      hmr: true,
      //  反向代理
      proxy: {
        "/api": {
          target: "http://localhost:9999/blogManage/",
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, ""),
        },
      },
    },
    /**
     * element-plus
     */
    build: {
      // 启用/禁用 CSS 代码拆分
      cssCodeSplit: true,
      // 构建后是否生成 source map 文件
      sourcemap: false,
      outDir: serviceConfig.OUTPUT_DIR,
      rollupOptions: {
        // external: ["vue"],
        output: {
          // globals: {
          //   vue: "Vue",
          // },
          manualChunks: {
            "element-plus": ["element-plus"],
          },
        },
      },
      chunkSizeWarningLimit: 600,
    },
    css: {
      preprocessorOptions: {
        less: {
          javascriptEnabled: true,
        },
      },
    },
  });
};
