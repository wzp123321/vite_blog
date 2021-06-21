<template>
  <img
    :src="url"
    alt=""
    :style="{ width: formatSize(width), height: formatSize(height) }"
    @click="onImagePreview({url})"
  />
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { useWebCommonSetting } from "../hooks/common/useWebCommonSetting";

export default defineComponent({
  name: "ImagePreview",
  props: {
    url: {
      type: String,
      default: "",
    },
    width: {
      type: Number || String,
      default: 80,
    },
    height: {
      type: Number || String,
      default: 80,
    },
  },
  setup(props) {
    const { url, width, height } = props;
    const { formatSize, onImagePreview } = useWebCommonSetting();

    return { url, width, height, formatSize, onImagePreview };
  },
});
</script>
<style lang="less" scoped>
img {
  cursor: pointer;
}
img:hover {
  transform: scale(1.2);
  transition: all 300ms;
}
</style>
