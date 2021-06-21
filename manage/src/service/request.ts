/*eslint-disable*/
import axios, { AxiosResponse } from "axios";
import ServiceConfig from "../config/constant";

// 定义一个空的数组，用于存放请求中的参数

// 创建axios实例
const axiosInstance = () => {
  const instance = axios.create({
    baseURL: ServiceConfig.BASE_URL,
    timeout: ServiceConfig.TIMEOUT
  });
  return instance;
};

// 请求实例
const publicReq = async (params: { [key: string]: any }) => {
  const { url, method, param } = params;
  const instance = axiosInstance();
  return await instance({
    url,
    method,
    // 在请求头里面添加token 如果没有则为空字符串
    headers: {
      ['s-Auth-Token']:
        localStorage.getItem("manage_token") === null
          ? ""
          : localStorage.getItem("manage_token")
    },
    [method === "post" ? "data" : "params"]: param || {},
    transformRequest: [
      function(data: { [key: string]: any }) {
        let ret = "";
        let index = 0;
        for (let key in data) {
          if (data[key] === null) {
            ret += `${index === 0 ? "" : "&"}key=&`;
          } else {
            ret += `${index === 0 ? "" : "&"}${encodeURIComponent(
              key
            )}=${encodeURIComponent(data[key])}`;
          }
          index += 1;
        }
        return ret;
      }
    ]
  }).then((res: AxiosResponse) => {
    if (res) {
      if (res.data.code === 403) {
        window.location.href = "/";
        localStorage.clear();
        throw new Error(res.statusText);
      }
      if (res.data.code !== 200) {
        throw new Error(res.data.data);
      } else {
        return res.data;
      }
    }
  });
};

// 请求超时函数
const timeoutfn = (delay: number, url: string) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(`请求超时--${url}`);
    }, delay);
  });
};

// 单个请求 存在请求超时
export async function req(
  params: { [key: string]: any },
  delay = ServiceConfig.TIMEOUT
) {
  try {
    const response: any = await Promise.race([
      timeoutfn(delay, params.url),
      publicReq(params)
    ]);
    if (response.data.code === 401) {
      location.href = "/";
    }
    return response;
  } catch (error) {
    throw new Error(error);
  }
}

// GET request
export async function getRequest(url: string, param: { [key: string]: any }) {
  try {
    const response = await req({
      url,
      method: "get",
      param
    });
    return response;
  } catch (err) {
    console.log(err);
  }
}

// POST request
export async function postRequest(url: string, param: { [key: string]: any }) {
  try {
    const response = await req({
      url,
      method: "post",
      param
    });
    return response;
  } catch (err) {
    console.log(err);
  }
}
