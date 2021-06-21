import { getRequest, postRequest } from "../request";

export default {
  /**
   * 查询列表
   * @param params
   * @returns
   */
  async getEnumVoList(
    params = { page: 1 }
  ): Promise<
    HttpService.Responseive<
      HttpService.HttpListResponse<TableListManage.EnumVoInfo[]>
    >
  > {
    try {
      const url = "/enum/list";
      const res: HttpService.Responseive<
        HttpService.HttpListResponse<TableListManage.EnumVoInfo[]>
      > = await getRequest(url, params);
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 新增枚举
   * @param params code
   * @param params title
   * @returns
   */
  async getEnumVoAdd(params: {
    code: number;
    title: string;
  }): Promise<HttpService.Responseive<boolean>> {
    try {
      const url = "/enum/add";
      const res: HttpService.Responseive<boolean> = await postRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },
};
