<template>
  <div class="article-preview">
    <h1>{{ title }}</h1>
    <div v-html="content"></div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, onMounted, toRefs } from "vue";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";
import hljs from "highlight.js";
import Service from "../../../service/package/index";

export default defineComponent({
  setup() {
    const { getRoute } = useWebCommonSetting();
    const articleState = reactive({
      title: "",
      content: "",
    });
    onMounted(async () => {
      const id = getRoute().query.id;
      const res = await Service.ArticleService.getArticleInfoById({
        id: String(id),
      });
      if (res) {
        articleState.title = res.data.title;
        articleState.content = res.data.content;
      }

      highlighthandle()
    });

    const highlighthandle = async () => {
      await hljs;
      let highlight = document.querySelectorAll("code,pre");
      highlight.forEach((block:HTMLElement) => {
        hljs.highlightBlock(block);
      });
    };
    return {
      ...toRefs(articleState),
    };
  },
});
</script>
<style lang="less" scoped>
.article-preview {
  padding: 23px;
  h1 {
    margin-bottom: 40px;
    text-align: center;
  }
}
</style>
