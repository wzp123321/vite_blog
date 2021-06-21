<template>
  <el-dialog
    title="新增管理员"
    v-model="visible"
    width="30%"
    @close="getFormReset"
  >
    <el-form
      :model="adminForm"
      :rules="rules"
      ref="adminFormRef"
      class="demo-ruleForm"
    >
      <el-form-item prop="name">
        <el-input
          placeholder="请输入管理员名"
          v-model="adminForm.name"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          placeholder="请输入密码"
          v-model="adminForm.password"
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
  name: "AdminCreateModal",
  emits: ["add"],
  setup(props, { emit }) {
    console.log(props);
    const { getGlobalMessage, getFormReset } = useWebCommonSetting();
    const modalState = reactive({
      visible: false,
      adminForm: {
        name: "",
        password: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入管理员名",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
    });
    const adminFormRef = ref(null);
    // 弹框打开
    const show = () => {
      modalState.visible = true;
      modalState.adminForm = {
        name: "",
        password: "",
      };
    };

    // 表单提交
    const submitForm = async (): Promise<void> => {
      const form = unref(adminFormRef);
      if (!form) {
        return;
      }

      await (form as any).validate();
      try {
        const res = await Service.AdminService.getAdminVoAdd(
          modalState.adminForm
        );
        if (res && res.code == 200 && res.data) {
          getGlobalMessage("success", "新增成功！");
          modalState.visible = false;
          emit("add");
        } else {
          getGlobalMessage("error", "新增失败！");
        }
      } catch (error) {
        getGlobalMessage("error", "新增失败！");
      }
    };

    return {
      ...toRefs(modalState),
      adminFormRef,
      submitForm,
      show,
      getFormReset,
    };
  },
});
</script>
<style lang="less" scoped></style>
