import { getRequest, postRequest } from "../request";

export default {
  /**
   * 用户登录
   * @param params
   * @returns
   */
  async getAdminLogin(params: {
    username: string;
    password: string;
  }): Promise<HttpService.Responseive<SignForm.UserInfo>> {
    try {
      const url = "/admin/login";
      const result: HttpService.Responseive<SignForm.UserInfo> =
        await postRequest(url, params);
      return result;
    } catch (error) {
      throw new Error("请求失败");
    }
  },

  /**
   * 登陆状态校验
   * @param params
   * @returns
   */
  async userLoginStatusVerify(params: {
    [key: string]: string;
  }): Promise<HttpService.Responseive<SignForm.UserInfo>> {
    try {
      const url = "/admin/verify";
      const result: HttpService.Responseive<SignForm.UserInfo> =
        await getRequest(url, params);
      return result;
    } catch (error) {
      throw new Error("请求失败");
    }
  },
};
