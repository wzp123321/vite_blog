import { getRequest, postRequest } from "../request";

export default {
  /**
   * 获取文章列表
   * @param params
   * @returns
   */
  async getArticleList(
    params: { page: number; [key: string]: string | number | undefined } = {
      page: 1,
    }
  ): Promise<
    HttpService.Responseive<
      HttpService.HttpListResponse<TableListManage.ArticleVoInfo[]>
    >
  > {
    try {
      const url = "article/list";
      const res: HttpService.Responseive<
        HttpService.HttpListResponse<TableListManage.ArticleVoInfo[]>
      > = await getRequest(url, params);
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 新增文章
   * @param params
   * @returns
   */
  async getArticleCreate(
    params: FormPropsInfo.ArticleForm
  ): Promise<HttpService.Responseive<Boolean>> {
    try {
      const url = "article/add";
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
   * 编辑文章
   * @param params
   * @returns
   */
  async getArticleUpdate(
    params: FormPropsInfo.ArticleForm
  ): Promise<HttpService.Responseive<Boolean>> {
    try {
      const url = "article/update";
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
   * 获取文章详情
   * @param params
   * @returns
   */
  async getArticleInfoById(params: {
    id: String;
  }): Promise<HttpService.Responseive<TableListManage.ArticleVoInfo>> {
    try {
      const url = "article/getById";
      const res: HttpService.Responseive<TableListManage.ArticleVoInfo> =
        await getRequest(url, params);
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },

  /**
   * 更新状态
   * @param params
   * @returns
   */
  async getArticleUpdateStatusById(params: {
    id: string;
    status: number;
  }): Promise<HttpService.Responseive<Boolean>> {
    try {
      const url = "article/updateStatus";
      const res: HttpService.Responseive<Boolean> = await getRequest(
        url,
        params
      );
      return res;
    } catch (error) {
      throw new Error(error);
    }
  },
};
