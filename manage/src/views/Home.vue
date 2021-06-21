<template>
  <el-container class="box">
    <el-aside width="200px"><Slide></Slide></el-aside>
    <el-container>
      <el-header><Header></Header></el-header>
      <el-main v-loading="loading"><Content></Content></el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";

import Header from "@/views/layout/header/index.vue";
import Slide from "@/views/layout/Slider.vue";
import Content from "@/views/layout/Content.vue";
import { useWebCommonSetting } from "../hooks/common/useWebCommonSetting";

export default defineComponent({
  name: "App",
  components: {
    Header,
    Slide,
    Content,
  },
  setup() {
    const { getGlobalLoading } = useWebCommonSetting();

    const loading = computed(() => getGlobalLoading());

    return { loading };
  },
});
</script>
<style lang="less" scoped>
.box {
  height: 100%;
  background: var(--backColor);
  .el-header {
    background-color: var(--whiteColor);
    box-shadow: 0px 1px 5px 0px rgba(0, 0, 0, 0.03);
    line-height: 60px;
  }
  .el-main {
    position: relative;
    margin-top: 12px;
    background-color: var(--whiteColor);
  }
}
</style>
