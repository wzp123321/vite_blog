<template>
  <ViteBlogDialog
    ref="viteDialog"
    :title="!id ? '编辑字典' : '新增字典'"
    width="30%"
    @close="getFormReset(dicFormRef)"
    @submit="onFormSubmit"
  >
    <template v-slot:content>
      <el-form :model="dicForm" :rules="rules" ref="dicFormRef">
        <el-form-item prop="title">
          <el-input
            placeholder="请输入字典标题"
            v-model="dicForm.title"
          ></el-input>
        </el-form-item>
        <el-form-item prop="type">
          <el-select
            v-if="enumList && enumList.length"
            v-model="dicForm.type"
            placeholder="请选择枚举类型"
            @change="onTypeChange"
          >
            <el-option
              v-for="(item, index) in enumList"
              :key="index"
              :label="item.title"
              :value="item.code"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="parentId" v-if="dicForm.type == 2">
          <el-select
            v-model="dicForm.parentId"
            placeholder="请选择一级目录"
            @change="onFirstCatalogChange"
          >
            <el-option
              v-for="(item, index) in firstCatalogList"
              :key="index"
              :label="item.title"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="description">
          <el-input
            placeholder="请输入字典描述"
            v-model="dicForm.description"
          ></el-input>
        </el-form-item>
      </el-form>
    </template>
  </ViteBlogDialog>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs, ref, unref } from "vue";
import ViteBlogDialog from "../../../components/ViteBlogDialog/index.vue";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";
import { dictionaryController } from "../dictionaryController";
import Service from "../../../service/package/index";

interface DialogState {
  firstCatalogList: TableListManage.DictionaryVoInfo[];
  id: Number;
  dicForm: {
    title: String;
    type: Number | undefined;
    parentId: Number | undefined;
    parentTitle: String;
    description: String;
  };
}

export default defineComponent({
  components: {
    ViteBlogDialog,
  },
  props: {
    /**
     * 枚举类型
     */
    enumList: {
      type: Array,
      default: () => [],
    },
  },
  emits: ["success"],
  setup(props, { emit }) {
    const { enumList } = props;
    const { rules, getFirstCatalogList } = dictionaryController();
    const { getFormReset, getGlobalMessage } = useWebCommonSetting();
    /**
     * refs
     */
    const viteDialog = ref(null);
    const dicFormRef = ref(null);
    let dialogState: DialogState = reactive({
      firstCatalogList: [],
      id: 0,
      dicForm: {
        title: "",
        type: undefined,
        parentId: undefined,
        parentTitle: "",
        description: "",
      },
    });
    /**
     * 监听枚举类型变化
     */
    const onTypeChange = async (value: number) => {
      dialogState.dicForm.type = value;
      if (value == 2) {
        const res = await getFirstCatalogList(1);
        dialogState.firstCatalogList = res.list;
      }
    };
    /**
     * 一级目录选择
     */
    const onFirstCatalogChange = (value: number) => {
      dialogState.firstCatalogList.forEach((item) => {
        if (item.id == value) {
          dialogState.dicForm.parentTitle = item.title;
        }
      });
    };
    /**
     * 打开弹框
     */
    const show = (info?: TableListManage.DictionaryVoInfo) => {
      dialogState.dicForm = {
        title: "",
        type: undefined,
        parentId: undefined,
        parentTitle: "",
        description: "",
      };
      const dialog = unref(viteDialog);
      if (dialog) (dialog as any).operation(true);
      if (info && Object.keys(info).length !== 0) {
        const { id, title, type, parentId, parentTitle, description } = info;
        console.log(title, type, parentId, parentTitle, description);
        dialogState.id = id;
        dialogState.dicForm = {
          title,
          type,
          parentId,
          parentTitle,
          description,
        };
        if (type == 2) {
          onTypeChange(2);
        }
      } else {
        dialogState.id = 0;
      }
    };

    /**
     * 表单提交
     */
    const onFormSubmit = async () => {
      const form = unref(dicFormRef);
      await (form as any).validate();
      const params = JSON.parse(JSON.stringify(dialogState.dicForm));
      if (!params.parentId) {
        delete params.parentId;
      }
      if (!params.parentTitle) {
        delete params.parentTitle;
      }
      if (!params.description) {
        delete params.description;
      }
      const { id } = dialogState;
      if (id) {
        Object.assign(params, { id });
      }
      const res = !id
        ? await Service.DictionaryService.getDictionaryAdd(params)
        : await Service.DictionaryService.getDictionaryUpdate(params);
      if (res && res.data && res.message == "SUCCESS") {
        getGlobalMessage("success", id ? "编辑成功！" : "新增成功！");
        const dialog = unref(viteDialog);
        if (dialog) (dialog as any).operation(false);
        emit("success");
      }
    };

    return {
      ...toRefs(dialogState),
      ...toRefs(enumList),
      rules,
      show,
      viteDialog,
      onFormSubmit,
      getFormReset,
      dicFormRef,
      onTypeChange,
      onFirstCatalogChange,
    };
  },
});
</script>
<style lang="less" scoped></style>
