/**
 * Used to package and output gzip. Note that this does not work properly in Vite, the specific reason is still being investigated
 * https://github.com/anncwb/vite-plugin-compression
 */
import type { Plugin } from "vite";

import vitePluginCompression from "vite-plugin-compression";

export function configCompressPlugin(
  compress: "gzip" | "none",
  deleteOriginFile: boolean = false
): Plugin | Plugin[] {
  const compressList = compress.split(",");

  const plugins: Plugin[] = [];

  if (compressList.includes("gzip")) {
    plugins.push(
      vitePluginCompression({
        verbose: true,
        disable: false,
        threshold: 10240,
        filter: /\.(js|css)?$/i, // 哪些文件要压缩
        ext: ".gz",
        algorithm: "gzip",
        deleteOriginFile,
      })
    );
  }
  return plugins;
}
