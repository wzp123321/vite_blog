<template>
  <div class="enum-manage">
    <CommonHeader title="新增枚举" @click="onAddModalShow"></CommonHeader>
    <el-table :data="enumList" style="width: 100%" align="center">
      <el-table-column type="index" label="序号" width="200">
        <template #default="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="code" label="Code"> </el-table-column>

      <el-table-column prop="title" label="枚举名"> </el-table-column>
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
    </el-table>

    <EnumCreateModal ref="addModalRef" @add="getList"></EnumCreateModal>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref, unref, reactive, toRefs } from "vue";
import { useEnumOperationController } from "./enumController";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

import EnumCreateModal from "./components/enumCreateModal.vue";
import CommonHeader from "../../components/CommonHeader.vue";

export default defineComponent({
  components: {
    EnumCreateModal,
    CommonHeader,
  },
  setup() {
    const addModalRef = ref(null);
    const enumState: { enumList: TableListManage.EnumVoInfo[] } = reactive({
      enumList: [],
    });
    const { getEnumList } = useEnumOperationController();
    const { getTimeStampFormat } = useWebCommonSetting();

    const onAddModalShow = () => {
      const modal = unref(addModalRef);
      if (!modal) {
        return;
      }
      (modal as any).show();
    };

    const getList = async () => {
      enumState.enumList = await getEnumList();
    };

    onMounted(() => {
      getList();
    });

    return {
      getList,
      ...toRefs(enumState),
      getTimeStampFormat,
      onAddModalShow,
      addModalRef,
    };
  },
});
</script>
