import { reactive, toRefs, unref, ref } from "@vue/reactivity";
import Service from "../../../service/package/index";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";

interface BannerState {
  articleList: TableListManage.ArticleVoInfo[];
  loading: Boolean;
  bannerForm: {
    title: String;
    articleId: String;
    imageUrl: String | any;
    state: Number;
  };
}

export const useBannerCreateController = () => {
  const bannerState = reactive<BannerState>({
    articleList: [],
    bannerForm: {
      title: "",
      articleId: "",
      imageUrl: "",
      state: 1,
    },
    loading: false,
  });
  const rules = {
    title: [
      {
        required: true,
        message: "请输入Banner标题",
        trigger: "blur",
      },
    ],
    articleId: [
      {
        required: true,
        message: "请选择对应文章",
        trigger: "change",
      },
    ],
    imageUrl: [
      {
        required: true,
        message: "请输入上传封面",
        trigger: "change",
      },
    ],
  };
  let bannerFormRef = ref(null);

  const { getRoute, getGlobalMessage, goBack, setGlobalLoading } =
    useWebCommonSetting();
  const id = Number(getRoute().query.id);

  /**
   * 获取文章列表
   */
  const getTotalArticleList = () => {
    let page = 1;
    let list: TableListManage.ArticleVoInfo[] = [];

    const getPage = async (page: number) => {
      const res = await Service.ArticleService.getArticleList({ page });
      if (res && res.code == 200 && res.data && res.message == "SUCCESS") {
        list = [...list, ...res.data.list];
        if (res.data.total !== 0 && res.data.total > list.length) {
          page += 1;
          getPage(page);
        } else {
          bannerState.articleList = list;
        }
      }
    };
    getPage(page);
  };

  /**
   * 操作
   */
  const getBannerVoOperation = async () => {
    const form = unref(bannerFormRef);
    form && (await (form as any).validate());
    bannerState.loading = true;
    let params = bannerState.bannerForm;

    if (id) {
      Object.assign(params, { id });
    }
    try {
      const res = !id
        ? await Service.BannerService.getBannerCreate(params)
        : await Service.BannerService.getBannerUpdate(params);
      if (res && res.code == 200 && res.data && res.message == "SUCCESS") {
        getGlobalMessage("success", id ? "编辑成功！" : "新增成功！");
        bannerState.loading = false;
        goBack();
      } else {
        getGlobalMessage("error", id ? "编辑失败！" : "新增失败！");
        bannerState.loading = false;
      }
    } catch (error) {
      getGlobalMessage("error", id ? "编辑失败！" : "新增失败！");
      bannerState.loading = false;
    }
  };

  /**
   * 获取Banner详情
   * @param id
   */
  const getBannerInfoById = async () => {
    if (!id) {
      return;
    }
    try {
      setGlobalLoading(true);
      const res = await Service.BannerService.getBannerVoInfoById({ id });
      if (res && res.code == 200 && res.data) {
        const { title, articleId, imageUrl, state } = res.data;
        Object.assign(bannerState.bannerForm, {
          title,
          articleId,
          imageUrl,
          state,
        });
        setGlobalLoading(false);
      }
    } catch (error) {
      setGlobalLoading(false);
      getGlobalMessage("error", "请求详情失败！");
    }
  };

  return {
    ...toRefs(bannerState),
    rules,
    bannerFormRef,
    getBannerVoOperation,
    getTotalArticleList,
    getBannerInfoById,
  };
};
