<template>
  <div class="article-list">
    <CommonHeader
      title="新增文章"
      @click="getRouter().push('/articleManage/create')"
    >
      <SelectHeader
        :searchParams="searchParams"
        @select="onArticleSelect"
      ></SelectHeader>
    </CommonHeader>
    <el-table :data="articleList" style="width: 100%" align="center">
      <el-table-column type="index" label="序号" width="100">
        <template #default="scope">
          {{ (pagination.page - 1) * 10 + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="标题" prop="title"></el-table-column>
      <el-table-column label="预览图">
        <template #default="scope">
          <ImagePreview :url="scope.row.imgUrl"></ImagePreview>
        </template>
      </el-table-column>
      <el-table-column
        label="一级目录"
        prop="firstCatalogName"
      ></el-table-column>
      <el-table-column
        label="二级目录"
        prop="secondCatalogName"
      ></el-table-column>
      <el-table-column label="描述" prop="description"></el-table-column>
      <el-table-column label="标签">
        <template #default="scope">
          <el-tag
            :type="colors[getRandomNum(colors.length)]"
            v-for="(item, index) in formatTag(scope.row.tags)"
            :key="index"
          >
            {{ item }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间">
        <template #default="scope">
          {{ getTimeStampFormat(scope.row.createTime) }}
        </template>
      </el-table-column>
      <el-table-column label="修改时间">
        <template #default="scope">
          {{ getTimeStampFormat(scope.row.modifyTime) }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template #default="scope">
          <el-select
            :model-value="scope.row.status"
            @change="(value) => onArticleStatusChange(value, scope.row.id)"
          >
            <el-option
              v-for="item in statuses"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <span
            class="action-span"
            @click="getRouterLinkTo(`/articleManage/update?id=${scope.row.id}`)"
            >编辑</span
          >
          <el-divider direction="vertical"></el-divider>
          <span
            class="action-span"
            @click="
              getRouterLinkTo(`/articleManage/preview?id=${scope.row.id}`)
            "
            >预览</span
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from "vue";

import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";
import { useArticleListController } from "./useArticleListController";
import CommonHeader from "../../../components/CommonHeader.vue";
import ImagePreview from "../../../components/ImagePreview.vue";
import SelectHeader from "./components/SelectHeader.vue";

export default defineComponent({
  name: "ArticleList",
  components: {
    CommonHeader,
    ImagePreview,
    SelectHeader,
  },
  setup() {
    const { getRouter, getTimeStampFormat, getRandomNum, getRouterLinkTo } =
      useWebCommonSetting();
    const {
      getArticleList,
      articleList,
      pagination,
      getTagsList,
      formatTag,
      colors,
      statuses,
      onArticleStatusChange,
      searchParams,
      onArticleSelect,
    } = useArticleListController();

    onMounted(() => {
      getTagsList();
      getArticleList();
    });
    return {
      getRouter,
      articleList,
      pagination,
      getTimeStampFormat,
      formatTag,
      getRandomNum,
      getRouterLinkTo,
      colors,
      statuses,
      searchParams,
      onArticleStatusChange,
      onArticleSelect,
    };
  },
});
</script>
<style lang="less" scoped></style>
