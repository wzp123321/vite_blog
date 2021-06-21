const miniCssExtractPlugin = require("mini-css-extract-plugin");
import type { Plugin } from "vite";

export const configMiniCssExtractPlugin = (): Plugin => {
  return new miniCssExtractPlugin({
    filename: `css/[hash].css`,
  });
};
