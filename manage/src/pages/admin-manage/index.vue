<template>
  <div class="admin-manage">
    <CommonHeader title="新增管理员" @click="onAddModalShow"></CommonHeader>
    <el-table :data="adminList" style="width: 100%" align="center">
      <el-table-column type="index" label="序号" width="200">
        <template #default="scope">
          {{ (pagination.page - 1) * 10 + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名"> </el-table-column>

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
      <el-table-column label="操作">
        <template #default="scope">
          <span class="action-span" @click="getAdminDelete(scope.row.name)"
            >删除</span
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :total="pagination.total"
      :current-page="pagination.page"
      @current-change="paginationChange"
    ></el-pagination>
    <AdminCreateModal
      ref="addModalRef"
      @add="paginationChange(1)"
    ></AdminCreateModal>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref, unref } from "vue";

import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";
import { useAdminOperationController } from "./adminController";

import CommonHeader from "../../components/CommonHeader.vue";
import AdminCreateModal from "./components/AdminCreateModal.vue";

export default defineComponent({
  components: {
    AdminCreateModal,
    CommonHeader,
  },
  setup() {
    const {
      pagination,
      adminList,
      getAdminList,
      getAdminDelete,
      paginationChange,
    } = useAdminOperationController();
    const { getTimeStampFormat } = useWebCommonSetting();
    const addModalRef = ref(null);
    /**
     * 初始化
     */
    onMounted(() => {
      getAdminList();
    });

    const onAddModalShow = () => {
      const modal = unref(addModalRef);
      if (!modal) {
        return;
      }
      (modal as any).show();
    };

    /**
     * 删除管理员
     */

    return {
      getAdminList,
      pagination,
      adminList,
      getAdminDelete,
      onAddModalShow,
      addModalRef,
      paginationChange,
      getTimeStampFormat,
    };
  },
});
</script>
