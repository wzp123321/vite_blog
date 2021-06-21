<template>
  <div class="dictionary-manage">
    <CommonHeader title="新增字典" @click="onUpdateDialogShow()">
      <template #default>
        <el-select
          v-model="type"
          placeholder="请选择枚举类型"
          :clearable="true"
        >
          <el-option
            v-for="(item, index) in enumList"
            :key="index"
            :label="item.title"
            :value="item.code"
          ></el-option>
        </el-select>
      </template>
    </CommonHeader>

    <el-table :data="dictionaryList" style="width: 100%" align="center">
      <el-table-column type="index" label="序号" width="200">
        <template #default="scope">
          {{ (pagination.page - 1) * 10 + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="字典名称"> </el-table-column>
      <el-table-column label="类型">
        <template #default="scope">
          {{ getDicType(scope.row.type) }}
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
      <el-table-column prop="description" label="描述"> </el-table-column>
      <el-table-column prop="parentTitle" label="所属一级目录">
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <span class="action-span" @click="onUpdateDialogShow(scope.row)"
            >编辑</span
          >
          <el-divider direction="vertical"></el-divider>
          <span class="action-span" @click="getDicDelete(scope.row.id)"
            >删除</span
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :total="pagination.total"
      :current-page="pagination.page"
      @current-change="onPaginationChange"
    ></el-pagination>
    <DictionaryAddModal
      ref="dicDialog"
      :enumList="enumList"
      @success="getDictionaryListQuery"
    ></DictionaryAddModal>
  </div>
</template>
<script lang="ts">
import {
  defineComponent,
  onMounted,
  watch,
  ref,
  unref,
  reactive,
  toRefs,
} from "vue";
import { dictionaryController } from "./dictionaryController";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";
import { useEnumOperationController } from "../enum-manage/enumController";

import DictionaryAddModal from "./components/DictionaryAddModal.vue";
import CommonHeader from "../../components/CommonHeader.vue";

export default defineComponent({
  components: {
    DictionaryAddModal,
    CommonHeader,
  },
  setup() {
    const { getEnumList } = useEnumOperationController();
    const {
      dictionaryList,
      getDictionaryListQuery,
      type,
      pagination,
      onPaginationChange,
      getDicDelete,
    } = dictionaryController();
    const { getTimeStampFormat } = useWebCommonSetting();
    const dicDialog = ref(null);
    const dicState: {
      enumList: TableListManage.EnumVoInfo[];
    } = reactive({
      enumList: [],
    });

    watch(type, () => {
      getDictionaryListQuery();
    });

    onMounted(async () => {
      try {
        dicState.enumList = await getEnumList();
        getDictionaryListQuery();
      } catch (error) {
        console.log(error);
      }
    });
    /**
     * 打开弹框
     */
    const onUpdateDialogShow = (info?: TableListManage.DictionaryVoInfo) => {
      const form = unref(dicDialog);
      if (form) {
        (form as any).show(info);
      }
    };
    /**
     * 字典类别
     */
    const getDicType = (value: number) => {
      let title = "";
      dicState.enumList.forEach((item) => {
        if (item.code == value) {
          title = item.title;
        }
      });
      return title;
    };

    return {
      ...toRefs(dicState),
      dictionaryList,
      pagination,
      type,
      dicDialog,
      onUpdateDialogShow,
      getTimeStampFormat,
      getDicType,
      onPaginationChange,
      getDicDelete,
      getDictionaryListQuery
    };
  },
});
</script>
