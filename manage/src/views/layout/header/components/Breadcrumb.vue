<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item
      v-for="(item, index) in list"
      :key="index"
      @click="
        () => {
          getRouterLinkTo(item.path);
        }
      "
      >{{ item.meta.name }}</el-breadcrumb-item
    >
  </el-breadcrumb>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs, watchEffect } from "vue";
import { useRoute } from "vue-router";
import { useWebCommonSetting } from "../../../../hooks/common/useWebCommonSetting";

export default defineComponent({
  name: "Breadcrumb",
  setup() {
    const route = useRoute();
    let routeState = reactive({
      list: route.matched,
    });
    const { getRouterLinkTo } = useWebCommonSetting();

    watchEffect(() => {
      routeState.list = route.matched;
    });
    return { ...toRefs(routeState), getRouterLinkTo };
  },
});
</script>
<style lang="less">
::v-deep .el-breadcrumb__item {
  cursor: pointer;
}
</style>
