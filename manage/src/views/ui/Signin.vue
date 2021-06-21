<template>
  <div class="bj">
    <div class="signin">
      <div class="signin-header">
        <div class="title">博客管理系统</div>
        <div class="signin-header-img"></div>
      </div>
      <div class="signin-form flex">
        <div class="signin-form-left"></div>
        <div class="signin-form-content">
          <el-col :span="15" :offset="2">
            <el-form ref="ruleFormsss" :model="signForm" :rules="rules">
              <el-form-item prop="username">
                <el-input
                  v-model="username"
                  placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input
                  type="password"
                  v-model="password"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-button
                  :loading="loading"
                  type="primary"
                  style="width: 360px; margin-top: 24px"
                  @click="onSubmit"
                  >立即登录</el-button
                >
              </el-form-item>
            </el-form>
          </el-col>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, toRefs, reactive, unref } from "vue";
import { useStore } from "vuex";
import Service from "../../service/package/index";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

export default defineComponent({
  name: "Signin",
  setup() {
    const { getRouterLinkTo, getGlobalMessage } = useWebCommonSetting();
    const ruleFormsss = ref(null);
    const { commit } = useStore();
    /**
     * ref(xx)----------reactive({value:xxx})
     */
    let loading = ref(false);
    const rules = reactive({
      username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
    });
    let signForm = reactive({
      username: "",
      password: "",
    });
    /**
     * 提交
     */
    const onSubmit = async (): Promise<void> => {
      const formRef = unref(ruleFormsss);
      if (!formRef) {
        return;
      }
      try {
        await (formRef as any).validate();
        const { username, password } = signForm;
        loading.value = true;
        const res = await Service.SigninService.getAdminLogin({
          username,
          password,
        });
        if (res && res.code === 200 && res.message === "SUCCESS") {
          commit("setUserLoginInfo", res.data);
          getRouterLinkTo("/");
          loading.value = false;
          getGlobalMessage("success", "登陆成功！");
        } else {
          (formRef as any).resetFields();
          loading.value = false;
          getGlobalMessage("error", "账号或密码错误！");
        }
      } catch (error) {
        loading.value = false;
        console.log(error);
      }
    };
    return {
      loading,
      rules,
      signForm,
      ...toRefs(signForm),
      onSubmit,
      ruleFormsss,
    };
  },
});
</script>

<style lang="less">
.bj {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/images/signin/bj.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  .signin {
    width: 1200px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    &-header {
      width: 100%;
      height: 120px;
      .title {
        color: #fff;
        font-size: 24px;
        text-shadow: 0 0 0.3rem #00d8ff;
        text-align: center;
      }
      &-img {
        background: url(../../assets/images/signin/head.gif) no-repeat center
          center;
        background-size: 100%;
        height: 90px;
        width: 100%;
        position: relative;
        top: -10px;
      }
    }
    &-form {
      height: 500px;
      overflow: hidden;
      &-left {
        width: 600px;
        background-image: url("../../assets/images/signin/d.png");
        background-size: contain;
        background-repeat: no-repeat;
        animation: rotation 15s linear infinite;
      }
      &-content {
        position: relative;
        width: 600px;
        background-image: url("../../assets/images/signin/e.png");
        background-size: contain;
        background-repeat: no-repeat;
        .el-form {
          position: absolute;
          left: 50%;
          top: 50%;
          transform: translate(-65%, -30%);
          .el-form-item {
            margin-bottom: 32px;
            .el-input input {
              background-image: url("../../assets/images/signin/b.png");
              background-size: 100% 100%;
              background-repeat: no-repeat;
              border-color: #010a16;
              background-color: #010a16;
              color: #fff;
            }
            input::-webkit-input-placeholder {
              color: #fff;
            }
            .el-button {
              background-image: url("../../assets/images/signin/a.png");
              background-size: 100% 100%;
              background-repeat: no-repeat;
              border-color: #59f1ff;
              background-color: #59f1ff;
            }
          }
        }
      }
    }
  }
}
@keyframes rotation {
  to {
    transform: rotate(360deg);
  }
}
</style>
