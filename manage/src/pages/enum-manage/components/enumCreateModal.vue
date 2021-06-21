<template>
  <el-dialog
    title="新增枚举"
    v-model="visible"
    width="30%"
    @close="getFormReset(enumFormRef)"
  >
    <el-form :model="enumForm" :rules="rules" ref="enumFormRef">
      <el-form-item prop="code">
        <el-input
          placeholder="请输入枚举类型"
          v-model="enumForm.code"
        ></el-input>
      </el-form-item>
      <el-form-item prop="title">
        <el-input
          placeholder="请输入枚举名"
          v-model="enumForm.title"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts">
import { defineComponent, reactive, ref, toRefs, unref } from "vue";
import Service from "../../../service/package/index";
import { useWebCommonSetting } from "../../../hooks/common/useWebCommonSetting";

export default defineComponent({
  name: "EnumCreateModal",
  emits: ["add"],
  setup(props, { emit }) {
    console.log(props);
    const { getGlobalMessage, getFormReset } = useWebCommonSetting();
    const enumFormRef = ref(null);

    const modalState = reactive({
      visible: false,
      enumForm: {
        code: 0,
        title: "",
      },
      rules: {
        code: [
          {
            required: true,
            message: "请选择枚举类型",
            trigger: "blur",
          },
        ],
        title: [
          {
            required: true,
            message: "请输入枚举名",
            trigger: "blur",
          },
        ],
      },
    });

    // 弹框打开
    const show = () => {
      modalState.visible = true;
      modalState.enumForm = {
        code: 0,
        title: "",
      };
    };

    // 表单提交
    const submitForm = async (): Promise<void> => {
      const form = unref(enumFormRef);
      if (!form) {
        return;
      }
      console.log(form);
      await (form as any).validate();
      try {
        const res = await Service.EnumService.getEnumVoAdd(modalState.enumForm);
        if (res && res.code == 200 && res.data && res.message !== "FAIL") {
          getGlobalMessage("success", "新增成功！");
          modalState.visible = false;
          emit("add");
        } else {
          getGlobalMessage("error", `新增失败【${res.data}】`);
        }
      } catch (error) {
        getGlobalMessage("error", "新增失败！");
      }
    };

    return {
      ...toRefs(modalState),
      enumFormRef,
      submitForm,
      show,
      getFormReset,
    };
  },
});
</script>
<style lang="less" scoped></style>
