import vue from "@vitejs/plugin-vue";
import tsconfigPaths from "vite-tsconfig-paths";
import type { Plugin } from "vite";

import { configMiniCssExtractPlugin } from "./configMiniCssExtractPlugin";
import { configHtmlPlugin } from "./configHtmlPlugin";
import { configImageminPlugin } from "./configImageMinPlugin";
import { configCompressPlugin } from "./configCompressPlugin";

const env = process.env.NODE_ENV;

export const createVitePlugins = () => {
  let vitePlugins: (Plugin | Plugin[])[] = [vue(), tsconfigPaths()];

  vitePlugins.push(configHtmlPlugin());
  vitePlugins.push(configMiniCssExtractPlugin());
  vitePlugins.push(configImageminPlugin());
  vitePlugins.push(
    configCompressPlugin(
      env === "development" ? "none" : "gzip",
      env !== "development"
    )
  );

  return vitePlugins;
};
