/**
 * 超时时长
 */
const TIMEOUT = 6000;
const env = import.meta.env.MODE;

const baseUrls: { [key: string]: string } = {
  development: "http://localhost:9527/api",
  test: "http://localhost:9998/blogManage/",
  production: "http://localhost:9997/blogManage/",
};
const BASE_URL = baseUrls[env];

export default {
  TIMEOUT,
  BASE_URL,
};
