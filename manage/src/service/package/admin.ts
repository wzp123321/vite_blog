import { getRequest, postRequest } from "../request";

export default {
  /**
   * 查询管理员列表
   * @param params
   * @returns
   */
  async getAdminVoList(
    params: CommonModule.PaginationParams = { page: 1, limit: 10 }
  ): Promise<
    HttpService.Responseive<
      HttpService.HttpListResponse<TableListManage.AdminVoInfo[]>
    >
  > {
    try {
      const url = "/admin/list";
      const res: HttpService.Responseive<
        HttpService.HttpListResponse<TableListManage.AdminVoInfo[]>
      > = await getRequest(url, params);
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 删除管理员
   * @param params
   * @returns
   */
  async getAdminVoDelete(params: {
    name: string;
  }): Promise<HttpService.Responseive<boolean>> {
    try {
      const url = "/admin/delete";
      const res: HttpService.Responseive<boolean> = await postRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 新增管理员
   * @param params
   * @returns
   */
  async getAdminVoAdd(params: {
    name: string;
    password: string;
  }): Promise<HttpService.Responseive<boolean>> {
    try {
      const url = "/admin/add";
      const res: HttpService.Responseive<boolean> = await getRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },
};
