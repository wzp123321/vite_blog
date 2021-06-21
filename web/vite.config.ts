import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

const path = require("path");

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 9526,
    open: true,
    hmr: true,
    proxy: {
      "^/api": {
        target: "http://localhost:9999/blogWeb",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});
