<template>
  <div class="article-select flex">
    <el-input
      v-model="searchParams.keyword"
      :clearable="true"
      placeholder="请输入内容"
      class="width200"
    >
      <template #append>
        <el-button
          icon="el-icon-search"
          @change="(value) => onSearch(value, 'keyword')"
        ></el-button>
      </template>
    </el-input>
    <el-select
      v-model="searchParams.firstCatalogId"
      :clearable="true"
      @change="(value) => onSearch(value, 'firstCatalogId')"
      placeholder="请选择一级目录"
      class="width200"
    >
      <el-option
        v-for="(item, index) in firstCatalogList"
        :key="index"
        :label="item.title"
        :value="item.id"
      ></el-option>
    </el-select>

    <el-select
      v-model="searchParams.secondCatalogId"
      :clearable="true"
      v-if="secondCatalogList && secondCatalogList.length"
      @change="(value) => onSearch(value, 'secondCatalogId')"
      placeholder="请选择二级目录"
      class="width200"
    >
      <el-option
        v-for="(item, index) in secondCatalogList"
        :key="index"
        :label="item.title"
        :value="item.id"
      ></el-option>
    </el-select>

    <el-select
      v-model="searchParams.type"
      :clearable="true"
      @change="(value) => onSearch(value, 'type')"
      placeholder="请选择文章类别"
      class="width200"
    >
      <el-option
        v-for="(item, index) in articleTypeList"
        :key="index"
        :label="item.title"
        :value="item.id"
      ></el-option>
    </el-select>

    <el-select
      v-model="searchParams.tags"
      :clearable="true"
      @change="(value) => onSearch(value, 'tags')"
      placeholder="请选择tag"
      class="width200"
    >
      <el-option
        v-for="(item, index) in tagList"
        :key="index"
        :label="item.title"
        :value="item.id"
      ></el-option>
    </el-select>

    <el-select
      v-model="searchParams.status"
      :clearable="true"
      @change="(value) => onSearch(value, 'status')"
      placeholder="请选择状态"
      class="width200"
    >
      <el-option
        v-for="(item, index) in statuses"
        :key="index"
        :label="item.value"
        :value="item.key"
      ></el-option>
    </el-select>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useArticleListController } from "../useArticleListController";
import { useArticleController } from "../../create/useArticleController";

export default defineComponent({
  props: {
    searchParams: {
      type: Object,
      default: () => {},
    },
  },
  emits: ["select"],
  setup(props, { emit }) {
    const { searchParams } = props;
    const { statuses } = useArticleListController();
    const {
      firstCatalogList,
      secondCatalogList,
      tagList,
      articleTypeList,
      getTotalDictionaryList,
      onFirstCatalogChange,
    } = useArticleController();

    onMounted(() => {
      getTotalDictionaryList();
    });

    const onSearch = (value, type) => {
      if (type == "firstCatalogId") {
        onFirstCatalogChange(value);
      }
      emit("select", value, type);
    };

    return {
      searchParams,
      firstCatalogList,
      secondCatalogList,
      tagList,
      statuses,
      articleTypeList,
      onSearch,
    };
  },
});
</script>
<style lang="less" scoped>
.article-select {
  .el-select {
    margin-left: 24px;
  }
  .width200 {
    width: 200px;
  }
}
</style>
