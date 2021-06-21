// 超时时长
const TIMEOUT = 6000;
console.log("env----------------", process.env.NODE_ENV);
const env = String(process.env.NODE_ENV);

/**
 * The name of the configuration file entered in the production environment
 */
const GLOB_CONFIG_FILE_NAME = "_app.config.js";

/**
 * 打包输出目录
 */
const OUTPUT_DIR = "viteBlogWeb";

/**
 * 公共资源目录
 */
const PUBLIC_DIR = "public";

/**
 * http请求地址
 */
const baseUrls: { [key: string]: string } = {
  development: "http://localhost:9527/api/",
  test: "http://localhost:9998/blogManage/",
  production: "http://localhost:9997/blogManage/",
};
const BASE_URL = baseUrls[env];

/**
 * 文件上传地址
 */
const UPLOAD_URL =
  env === "production"
    ? "https://server.zpwan-yz.com/blogManage/filemodule/web/upload"
    : "http://127.0.0.1:9527/api/file/upload";

export default {
  TIMEOUT,
  BASE_URL,
  GLOB_CONFIG_FILE_NAME,
  OUTPUT_DIR,
  PUBLIC_DIR,
  UPLOAD_URL,
};
