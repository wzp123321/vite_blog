import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

const { replace } = require("path");

const path = require("path");

module.exports = defineConfig({
  plugins: [vue()],
  alias: {
    "/@/": path.resolve(__dirname, "./src"),
  },
  server: {
    port: 9526,
    hmr: true,
    proxy: {
      "^/api": {
        target: "http://localhost:9999/blogWeb/",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});
