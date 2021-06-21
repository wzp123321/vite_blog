/**
 * 表格
 */
declare namespace TableListManage {
  interface AdminVoInfo {
    createTime: number;
    id: number;
    modifyTime: number;
    name: string;
    password: string;
    salt: string;
  }

  interface EnumVoInfo {
    code: number;
    createTime: number;
    id: number;
    modifyTime: number;
    title: string;
  }

  interface DictionaryVoInfo {
    createTime: number;
    description: string;
    id: number;
    modifyTime: number;
    parentId: number;
    parentTitle: string;
    title: string;
    type: number;
  }

  interface BannerVoInfo {
    articleId: string;
    id: number;
    imageUrl: string;
    state: number;
    title: string;
  }

  interface ArticleVoInfo {
    content: string;
    createTime: number;
    description: string;
    firstCatalogId: number;
    firstCatalogName: string;
    id: string;
    imgUrl: string;
    md: string;
    modifyTime: number;
    secondCatalogId: number;
    secondCatalogName: string;
    status: number;
    tags: string;
    title: string;
    visitCount: number;
  }
}

/**
 * 表单提交
 */
declare namespace FormPropsInfo {
  interface DicionaryForm {
    id?: number;
    type: number | undefined;
    title: string;
    parentId?: number | undefined;
    prientTitle?: string;
    description?: string;
  }

  interface BannerForm {}

  interface ArticleForm {}
}
