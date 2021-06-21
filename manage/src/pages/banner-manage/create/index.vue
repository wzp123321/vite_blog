<template>
  <div class="banner-operation">
    <el-form
      :model="bannerForm"
      :rules="rules"
      ref="bannerFormRef"
      label-width="100px"
    >
      <el-form-item label="Banner标题" prop="title">
        <el-input
          v-model="bannerForm.title"
          class="width360"
          placeholder="请输入Banner标题"
        ></el-input>
      </el-form-item>
      <el-form-item label="所属文章" prop="articleId">
        <el-select
          class="width360"
          v-model="bannerForm.articleId"
          placeholder="请选择对应文章"
        >
          <el-option
            v-for="(item, index) in articleList"
            :key="index"
            :label="item.title"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="封面" prop="imageUrl ">
        <FileUploader
          :imgUrl="bannerForm.imageUrl"
          @update:imgUrl="bannerForm.imageUrl = $event"
        ></FileUploader>
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-switch
          v-model="bannerForm.state"
          :active-value="1"
          :inactive-value="0"
        ></el-switch>
      </el-form-item>
      <el-form-item style="margin-top: 80px">
        <el-button @click="goBack">返回</el-button>
        <el-button
          type="primary"
          :loading="loading"
          @click="getBannerVoOperation"
          >立即创建</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";
import { useBannerCreateController } from "./useBannerCreateController";
import FileUploader from "../../../components/FileUploader.vue";

export default defineComponent({
  components: {
    FileUploader,
  },
  setup() {
    const { goBack, getRoute } = useWebCommonSetting();
    const {
      loading,
      bannerForm,
      rules,
      bannerFormRef,
      getBannerVoOperation,
      articleList,
      getTotalArticleList,
      getBannerInfoById,
    } = useBannerCreateController();
    const id = getRoute().query.id;

    onMounted(() => {
      getTotalArticleList();
      if (id) {
        getBannerInfoById();
      }
    });

    return {
      loading,
      goBack,
      bannerForm,
      rules,
      bannerFormRef,
      getBannerVoOperation,
      articleList,
    };
  },
});
</script>
<style lang="less" scoped></style>
