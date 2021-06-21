<template>
  <div class="banner-manage">
    <CommonHeader
      title="新增Banner"
      @click="getRouter().push('/bannerManage/create')"
    ></CommonHeader>
    <el-table :data="bannerList" style="width: 100%" align="center">
      <el-table-column type="index" label="序号" width="200">
        <template #default="scope">
          {{ (pagination.page - 1) * 10 + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题"> </el-table-column>
      <el-table-column label="预览图">
        <template #default="scope">
          <ImagePreview :url="scope.row.imageUrl"></ImagePreview>
        </template>
      </el-table-column>
      <el-table-column label="所属文章">
        <template #default="scope">
          <span
            class="action-span"
            @click="
              getRouter().push(
                `/articleManage/update?id=${scope.row.articleId}`
              )
            "
            >所属文章</span
          >
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <span
            class="action-span"
            @click="getRouter().push(`/bannerManage/update?id=${scope.row.id}`)"
            >编辑</span
          >
          <el-divider direction="vertical"></el-divider>
          <span class="action-span" @click="getBannerDelete(scope.row.id)"
            >删除</span
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";
import { useBannerListController } from "./useBannerListController";

import CommonHeader from "../../../components/CommonHeader.vue";
import ImagePreview from "../../../components/ImagePreview.vue";

export default defineComponent({
  components: {
    CommonHeader,
    ImagePreview,
  },
  setup() {
    const { getRouter } = useWebCommonSetting();
    const { getBannerList, bannerList, pagination, getBannerDelete } =
      useBannerListController();

    onMounted(() => {
      getBannerList();
    });

    return {
      getRouter,
      bannerList,
      pagination,
      getBannerDelete,
    };
  },
});
</script>
<style lang="less" scoped>
img {
  cursor: pointer;
  width: 100px;
  height: 100px;
  transition: all 300ms;
}
img:hover {
  transform: scale(1.1);
  transition: all 300ms;
}
</style>
