<template>
  <div class="article-operation">
    <el-form
      :model="articleForm"
      :rules="rules"
      ref="articleFormRef"
      label-width="100px"
    >
      <!-- 标题 -->
      <el-form-item label="文章标题" prop="title">
        <el-input
          class="width360"
          v-model="articleForm.title"
          placeholder="请输入文章标题"
        ></el-input>
      </el-form-item>
      <!-- 描述 -->
      <el-form-item label="文章描述" prop="description">
        <el-input
          class="width360"
          v-model="articleForm.description"
          type="textarea"
          placeholder="请输入文章描述"
        ></el-input>
      </el-form-item>
      <!-- tags -->
      <el-form-item label="文章tag" prop="tags">
        <el-select
          class="width360"
          v-model="articleForm.tags"
          multiple
          placeholder="请选择文章tag"
        >
          <el-option
            v-for="item in tagList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 一级目录 -->
      <el-form-item label="一级目录" prop="firstCatalogId">
        <el-select
          class="width360"
          v-model="articleForm.firstCatalogId"
          placeholder="请选择一级目录"
          @change="onFirstCatalogChange"
        >
          <el-option
            v-for="item in firstCatalogList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 二级目录 -->
      <el-form-item
        v-if="secondCatalogList && secondCatalogList.length"
        label="二级目录"
        prop="secondCatalogId"
      >
        <el-select
          class="width360"
          v-model="articleForm.secondCatalogId"
          placeholder="请选择二级目录"
          @change="onSecondCatalogChange"
        >
          <el-option
            v-for="item in secondCatalogList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 预览图 -->
      <el-form-item label="预览图" prop="imgUrl">
        <FileUploader
          @update:imgUrl="articleForm.imgUrl = $event"
          :imgUrl="articleForm.imgUrl"
        ></FileUploader>
      </el-form-item>
      <el-form-item label="文章内容" prop="content">
        <v-md-editor
          v-model="articleForm.md"
          :disabled-menus="[]"
          @upload-image="handleUploadImage"
          @change="onEditorChange"
        />
      </el-form-item>
    </el-form>
    <div class="article-operation-btn" style="text-align: center">
      <el-button @click="goBack">返回</el-button>
      <el-button type="primary" @click="submitForm()" :loading="loading"
        >确认</el-button
      >
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useArticleController } from "./useArticleController";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";

import FileUploader from "../../../components/FileUploader.vue";

export default defineComponent({
  name: "ArticleOperation",
  components: {
    FileUploader,
  },
  setup() {
    const {
      rules,
      loading,
      articleForm,
      articleFormRef,
      tagList,
      firstCatalogList,
      secondCatalogList,
      articleTypeList,
      onEditorChange,
      onFirstCatalogChange,
      onSecondCatalogChange,
      handleUploadImage,
      getTotalDictionaryList,
      submitForm,
      getArticleDetailById,
    } = useArticleController();

    const { goBack, getRoute } = useWebCommonSetting();

    onMounted(async () => {
      await getTotalDictionaryList();
      if (getRoute().query.id) {
        await getArticleDetailById();
      }
    });

    return {
      loading,
      rules,
      articleForm,
      articleFormRef,
      tagList,
      firstCatalogList,
      secondCatalogList,
      articleTypeList,
      onEditorChange,
      onFirstCatalogChange,
      onSecondCatalogChange,
      handleUploadImage,
      goBack,
      submitForm,
    };
  },
});
</script>
<style lang="less" scoped>
.article-operation {
  position: relative;
  padding-bottom: 50px;
  &-btn {
    position: fixed;
    left: 200px;
    bottom: 0;
    padding: 12px 0;
    background: #fff;
    width: calc(100% - 200px);
  }
  :deep(.v-md-editor) {
    min-height: 300px;
  }
}
.el-button {
  width: 100px;
}
</style>
