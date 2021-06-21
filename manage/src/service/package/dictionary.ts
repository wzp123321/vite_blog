import { getRequest, postRequest } from "../request";

export default {
  /**
   * 查询字典列表
   * @param params
   * @returns
   */
  async getDictionaryList(
    params: CommonModule.PaginationParams = { page: 1, limit: 10 }
  ): Promise<
    HttpService.Responseive<
      HttpService.HttpListResponse<TableListManage.DictionaryVoInfo[]>
    >
  > {
    try {
      const url = "dic/list";
      const res: HttpService.Responseive<
        HttpService.HttpListResponse<TableListManage.DictionaryVoInfo[]>
      > = await getRequest(url, params);
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 新增字典
   * @param params
   * @returns
   */
  async getDictionaryAdd(
    params: FormPropsInfo.DicionaryForm
  ): Promise<HttpService.Responseive<boolean>> {
    try {
      const url = "dic/add";
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
   * 编辑字典
   * @param params
   * @returns
   */
  async getDictionaryUpdate(
    params: FormPropsInfo.DicionaryForm
  ): Promise<HttpService.Responseive<boolean>> {
    try {
      const url = "dic/update";
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
   * 删除字典
   * @param params
   * @returns
   */
  async getDictionaryDelete(params: {
    id: number;
  }): Promise<HttpService.Responseive<boolean>> {
    try {
      const url = "dic/delete";
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
