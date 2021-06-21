<template>
  <div class="header">
    <span>{{ userName || "测试管理员" }}</span>
    <i class="iconfont icondengchu_o" @click="loginout"></i>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "HeaderComp",
  setup() {
    const store = useStore();
    const router = useRouter();
    const ctx = getCurrentInstance();
    const userName = computed(() => {
      return store.state.userName;
    });

    // 登出
    function loginout() {
      ctx?.appContext.config.globalProperties.$confirm(
        "你确认退出登录吗？",
        "退出登录",
        {
          type: "warning",
          confirmButtonText: "确定",
          showClose: false,
          callback: (action: string) => {
            if (action === "confirm") {
              router.push("/signin");
              window.localStorage.removeItem("mulit_token");
              ctx?.appContext.config.globalProperties.$message({
                type: "success",
                message: "退出登录",
              });
            }
          },
        }
      );
    }
    return { userName, loginout };
  },
});
</script>

<style lang="less" scoped>
.header {
  text-align: right;
  cursor: pointer;
  span {
    display: inline-block;
    font-size: var(--font_14);
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #262626;
    line-height: 12px;
    padding: 0 10px;
  }
}
</style>
