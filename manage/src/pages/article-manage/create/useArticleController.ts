import { reactive, toRefs, unref } from "@vue/reactivity";
import { useCommonUpload } from "../../../hooks/upload/useCommonUpload";
import Service from "../../../service/package/index";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";

interface ArticleState {
  articleForm: {
    title: string;
    content: string;
    md: string;
    description: string;
    imgUrl: string | any;
    tags: number[];
    firstCatalogId: number | undefined;
    firstCatalogName: string;
    secondCatalogId: number | undefined;
    secondCatalogName: string;
  };
  loading: Boolean;
  articleFormRef: any;
  tagList: TableListManage.DictionaryVoInfo[];
  firstCatalogList: TableListManage.DictionaryVoInfo[];
  secondCatalogList: TableListManage.DictionaryVoInfo[];
  articleTypeList: TableListManage.DictionaryVoInfo[];
}

export const useArticleController = () => {
  const { Deffer, getRoute, getGlobalMessage, goBack } = useWebCommonSetting();
  const { handleFileUpload } = useCommonUpload();
  const articleState = reactive<ArticleState>({
    articleForm: {
      title: "",
      tags: [],
      firstCatalogId: undefined,
      firstCatalogName: "",
      secondCatalogId: undefined,
      secondCatalogName: "",
      imgUrl: "",
      description: "",
      md: "",
      content: "",
    },
    loading: false,
    articleFormRef: null,
    tagList: [],
    firstCatalogList: [],
    secondCatalogList: [],
    articleTypeList: [],
  });
  let rules = {
    title: {
      required: true,
      message: "请输入文章标题",
      trigger: "blur",
    },
    tags: {
      required: true,
      message: "请输入选择tags",
      trigger: "change",
    },
    firstCatalogId: {
      required: true,
      message: "请输入选择一级目录",
      trigger: "change",
    },
    secondCatalogId: {
      required: true,
      message: "请输入选择二级目录",
      trigger: "change",
    },
    imgUrl: {
      required: true,
      message: "请上传预览图",
      trigger: "change",
    },
    content: {
      required: true,
      message: "请编辑文章内容",
      trigger: "change",
    },
  };
  const id = getRoute().query.id;
  let deffer;
  // 编辑器change事件
  const onEditorChange = (text, html) => {
    articleState.articleForm.md = text;
    articleState.articleForm.content = html;
  };

  /**
   * 文件上传
   */
  const handleUploadImage = async (e: any, insertImage: Function) => {
    const res = await handleFileUpload(e.target.files[0]);
    insertImage({
      url: res,
    });
  };
  /**
   * 公共方法----获取字典具体类别列表
   */
  const getDictionaryAllListByType = (type: number, parentId: number = 0) => {
    deffer = new Deffer();
    let page = 1;
    let params = { page, type };
    if (parentId) {
      Object.assign(params, { parentId });
    }
    let list: TableListManage.DictionaryVoInfo[] = [];
    const getPage = async (page: number) => {
      try {
        const res = await Service.DictionaryService.getDictionaryList(params);
        if (res && res.code == 200 && res.data) {
          list = [...list, ...res.data.list];
          if (list.length !== 0 && list.length < res.data.total) {
            page += 1;
            getPage(page);
          } else {
            deffer.resolve(list);
          }
        }
      } catch (error) {
        deffer.reject([]);
      }
    };
    getPage(page);
    return deffer.promise;
  };
  /**
   * 获取各类别字典全部列表
   */
  const getTotalDictionaryList = async () => {
    try {
      const promiseArr = [
        await getDictionaryAllListByType(0),
        await getDictionaryAllListByType(1),
        await getDictionaryAllListByType(3),
      ];
      const resArr = await Promise.all(promiseArr);
      if (resArr && resArr.length) {
        articleState.tagList = resArr[0];
        articleState.firstCatalogList = resArr[1];
        articleState.articleTypeList = resArr[2];
      }
    } catch (error) {
      console.log(error);
    }
  };
  /**
   * 一级目录change
   */
  const onFirstCatalogChange = async (value: number) => {
    articleState.firstCatalogList.forEach((item) => {
      if (item.id == value) {
        articleState.articleForm.firstCatalogName = item.title;
      }
    });
    try {
      const res = await getDictionaryAllListByType(2, value);
      console.log(res);
      if (res) {
        articleState.secondCatalogList = res;
      }
    } catch (error) {
      console.log(error);
    }
  };
  /**
   * 二级目录change
   * @param value
   */
  const onSecondCatalogChange = (value: number) => {
    articleState.secondCatalogList.forEach((item) => {
      if (item.id == value) {
        articleState.articleForm.secondCatalogName = item.title;
      }
    });
  };
  /**
   * 获取文章详情
   */
  const getArticleDetailById = async () => {
    try {
      const res = await Service.ArticleService.getArticleInfoById({
        id: String(id),
      });
      if (res && res.data) {
        const {
          title,
          tags,
          firstCatalogName,
          secondCatalogName,
          firstCatalogId,
          secondCatalogId,
          imgUrl,
          description,
          md,
          content,
        } = res.data;
        onFirstCatalogChange(firstCatalogId);
        console.log(
          tags.split(",").map((item) => {
            return Number(item);
          })
        );
        articleState.articleForm = {
          title,
          tags: tags.split(",").map((item) => {
            return Number(item);
          }),
          firstCatalogId,
          firstCatalogName,
          secondCatalogId,
          secondCatalogName,
          imgUrl,
          description,
          md,
          content,
        };
      }
    } catch (error) {
      console.log(error);
    }
  };
  /**
   * 表单提交
   */
  const submitForm = async () => {
    const form = unref(articleState.articleFormRef);
    form && (await form.validate());
    let params = articleState.articleForm;
    articleState.loading = true;
    if (id) {
      Object.assign(params, { id });
    }
    try {
      const res = !id
        ? await Service.ArticleService.getArticleCreate(params)
        : await Service.ArticleService.getArticleUpdate(params);

      if (res && res.code == 200 && res.message == "SUCCESS") {
        getGlobalMessage("success", id ? "编辑成功！" : "创建成功！");
        articleState.loading = false;
        goBack();
      } else {
        getGlobalMessage("error", id ? "编辑失败！" : "创建失败！");
        articleState.loading = false;
      }
    } catch (error) {
      getGlobalMessage("error", id ? "编辑失败！" : "创建失败！");
      articleState.loading = false;
    }
  };

  return {
    ...toRefs(articleState),
    rules,
    onEditorChange,
    handleUploadImage,
    onFirstCatalogChange,
    onSecondCatalogChange,
    getTotalDictionaryList,
    submitForm,
    getDictionaryAllListByType,
    getArticleDetailById,
  };
};
