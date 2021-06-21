<template>
  <div class="slider">
    <el-menu
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      style="height: 100%"
    >
      <el-menu-item
        v-for="(item, index) in list"
        :key="index"
        :index="`${String(index)}`"
        @click="
          () => {
            $router.push(item.path);
          }
        "
      >
        <i style="padding-right: 10px" :class="['iconfont', item.icon]"></i>
        <template #title>{{ item.name }}</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { createSlideList } from "../../plugins/createSlierList";

export default defineComponent({
  name: "SliderComp",
  setup() {
    const list: { path: string; name: string; icon: string }[] =
      createSlideList();

    return { list };
  },
});
</script>

<style lang="less" scoped>
.slider {
  height: 100%;
}
</style>
