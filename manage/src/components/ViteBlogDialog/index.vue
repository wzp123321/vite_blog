<template>
  <el-dialog
    :title="title"
    v-model="visible"
    :width="formatSize(width)"
    @close="close"
  >
    <slot name="content"></slot>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { useCommonDialog } from "./useCommonDialog";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

export default defineComponent({
  props: {
    /**
     * 标题
     */
    title: {
      type: String,
      default: "",
    },
    /**
     * 宽度
     */
    width: {
      type: String || Number,
      default: "30%",
    },
    /**
     * 字典详情 默认为空
     */
    dictionaryInfo: {
      type: Object,
      default: () => {},
    },
  },
  emits: ["close", "submit"],
  setup(props, { emit }) {
    const { width } = props;
    const { formatSize } = useWebCommonSetting();
    const { visible, operation } = useCommonDialog();

    const close = () => {
      operation(false);
      emit("close");
    };

    const onSubmit = () => {
      emit("submit");
    };

    return { visible, operation, close, onSubmit, formatSize, width };
  },
});
</script>
<style lang="less" scoped></style>
