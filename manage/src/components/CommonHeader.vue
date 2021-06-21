<template>
  <div class="common-header flex-row-justify-end">
    <slot></slot>
    <el-button @click="onClick" type="primary">{{ title }}</el-button>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  name: "CommonHeader",
  props: {
    title: {
      type: String,
      default: "新增模块",
    },
  },
  emits: ["click"],
  setup(props, { emit }) {
    const { title } = props;

    const onClick = () => {
      emit("click");
    };
    return { title, onClick };
  },
});
</script>
<style lang="less" scoped>
.common-header {
  padding-bottom: 12px;
  border-bottom: 1px dashed #eee;
}
</style>
