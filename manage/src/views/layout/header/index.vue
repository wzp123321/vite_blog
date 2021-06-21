<template>
  <div class="header flex-row-justify-center">
    <Breadcrumb></Breadcrumb>
    <div>
      <span>{{ userName || "测试管理员" }}</span>
      <i class="iconfont icondengchu_o" @click="getUserLoginOut"></i>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useHeaderSetting } from "../../../hooks/setting/useHeaderSetting";

import Breadcrumb from "./components/Breadcrumb.vue";

export default defineComponent({
  name: "HeaderComp",
  components: {
    Breadcrumb,
  },
  setup() {
    const { getHeaderUserName, getUserLoginOut } = useHeaderSetting();
    const userName = getHeaderUserName;

    return { userName, getUserLoginOut };
  },
});
</script>

<style lang="less" scoped>
.header {
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
