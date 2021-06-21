import { reactive, toRefs } from "@vue/reactivity";

import Service from "../../../service/package/index";
import { useArticleController } from "../create/useArticleController";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";

interface ArticleState {
  articleList: TableListManage.ArticleVoInfo[];
  tagList: TableListManage.DictionaryVoInfo[];
  pagination: CommonModule.PaginationParams;
  searchParams: {
    keyword?: string;
    firstCatalogId?: number;
    secondCatalogId?: number;
    status?: number;
    tags?: string;
    type?: number;
  };
}

export const useArticleListController = () => {
  const { getDictionaryAllListByType } = useArticleController();
  const { getGlobalMessage, setGlobalLoading } = useWebCommonSetting();
  const articleState = reactive<ArticleState>({
    articleList: [],
    tagList: [],
    pagination: {
      page: 1,
    },
    searchParams: {},
  });
  const colors = ["success", "info", "warning", "danger"];
  const statuses = [
    { key: 0, value: "删除" },
    { key: 1, value: "下架" },
    { key: 2, value: "上架" },
    { key: 3, value: "推荐" },
    { key: 4, value: "置顶" },
  ];
  /**
   * 获取文章列表
   */
  const getArticleList = async () => {
    setGlobalLoading(true);
    const { page } = articleState.pagination;
    const { keyword, firstCatalogId, secondCatalogId, tags, status } =
      articleState.searchParams;
    try {
      const res = await Service.ArticleService.getArticleList({
        page,
        keyword,
        firstCatalogId,
        secondCatalogId,
        tags,
        status,
      });
      if (res && res.data && res.code == 200 && res.message == "SUCCESS") {
        articleState.articleList = res.data.list;
        articleState.pagination.total = res.data.total;
        setGlobalLoading(false);
      } else {
        getGlobalMessage("error", "查询文章列表失败！");
        setGlobalLoading(false);
      }
    } catch (error) {
      getGlobalMessage("error", "查询文章列表失败！");
      setGlobalLoading(false);
    }
  };

  /**
   * 获取tag列表
   */
  const getTagsList = async () => {
    try {
      const res = await getDictionaryAllListByType(0);
      if (res) {
        articleState.tagList = res;
      }
    } catch (error) {
      console.error(error);
    }
  };
  /**
   *
   * @param tags 处理tag
   * @returns
   */
  const formatTag = (tags) => {
    const { tagList } = articleState;
    const list = tags.split(",");
    let titles: string[] = [];
    tagList.forEach((item) => {
      if (list.includes(String(item.id))) {
        titles.push(item.title);
      }
    });
    return titles;
  };
  const onArticleStatusChange = async (status: number, id: string) => {
    try {
      const res = await Service.ArticleService.getArticleUpdateStatusById({
        id,
        status,
      });
      if (res && res.code == 200 && res.message == "SUCCESS") {
        getGlobalMessage("success", "修改成功！");
        getArticleList();
      } else {
        getGlobalMessage("error", "修改失败！");
      }
    } catch (error) {
      getGlobalMessage("error", "修改失败！");
    }
  };

  const onArticleSelect = (value: number, type: string) => {
    articleState.searchParams[type] = value;
    if (type == "firstCatalogId") {
      delete articleState.searchParams.secondCatalogId;
    }
    console.log(typeof value);
    if (!value && Object.prototype.toString.call(value) =='[object String]') {
      delete articleState.searchParams[type];
    }
    getArticleList();
  };

  return {
    ...toRefs(articleState),
    getArticleList,
    getTagsList,
    formatTag,
    colors,
    statuses,
    onArticleStatusChange,
    onArticleSelect,
  };
};
