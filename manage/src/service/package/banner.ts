import { getRequest, postRequest } from "../request";

export default {
  /**
   * 获取banner列表
   * @param params
   * @returns
   */
  async getBannerList(
    params: { page: number } = { page: 1 }
  ): Promise<
    HttpService.Responseive<
      HttpService.HttpListResponse<TableListManage.BannerVoInfo[]>
    >
  > {
    try {
      const url = "/banner/list";
      const res: HttpService.Responseive<
        HttpService.HttpListResponse<TableListManage.BannerVoInfo[]>
      > = await getRequest(url, params);
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 新增banner
   * @param params
   * @returns
   */
  async getBannerCreate(
    params: FormPropsInfo.BannerForm
  ): Promise<HttpService.Responseive<Boolean>> {
    try {
      const url = "/banner/add";
      const res: HttpService.Responseive<Boolean> = await postRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * banner编辑
   * @param params
   * @returns
   */
  async getBannerUpdate(
    params: FormPropsInfo.BannerForm
  ): Promise<HttpService.Responseive<Boolean>> {
    try {
      const url = "/banner/update";
      const res: HttpService.Responseive<Boolean> = await postRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 删除Banner
   * @param params
   * @returns
   */
  async getBannerDelete(params: {
    id: number;
  }): Promise<HttpService.Responseive<Boolean>> {
    try {
      const url = "/banner/delete";
      const res: HttpService.Responseive<Boolean> = await getRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 获取Banner详情
   * @param params 
   * @returns 
   */
  async getBannerVoInfoById(params: {
    id: number;
  }): Promise<HttpService.Responseive<TableListManage.BannerVoInfo>> {
    try {
      const url = "/banner/getById";
      const res: HttpService.Responseive<TableListManage.BannerVoInfo> = await getRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },
};
