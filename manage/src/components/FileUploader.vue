<template>
  <div class="v-Uploader">
    <el-upload
      class="avatar-uploader"
      :style="{
        width: formatSize(width),
        height: formatSize(height),
      }"
      name="file"
      :multiple="multiple"
      :action="action"
      :accept="accept"
      :show-file-list="false"
      :on-success="handleUploaderSuccess"
    >
      <img v-if="imgUrl" :src="imgUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>
<script lang="ts">
import { defineComponent, toRefs } from "vue";
import { useWebCommonSetting } from "../hooks/common/useWebCommonSetting";
import serviceConfig from "../config/constant";

export default defineComponent({
  name: "FileUploaderComp",
  props: {
    width: {
      type: Number || String,
      default: 178,
    },
    height: {
      type: Number || String,
      default: 178,
    },
    /**
     * 是否支持批量上传
     */
    multiple: {
      type: Boolean,
      default: false,
    },
    /**
     * 支持的后缀名
     */
    accept: {
      type: String,
      default: ".png,.jpeg",
    },
    /**
     * 文件下载地址
     */
    imgUrl: {
      type: String,
      default: "",
    },
  },
  emits: ["update:imgUrl"],
  setup(props, { emit }) {
    const { width, height, imgUrl, accept, multiple } = toRefs(props);
    const { formatSize, getGlobalMessage } = useWebCommonSetting();
    const action = serviceConfig.UPLOAD_URL;

    const handleUploaderSuccess = (res) => {
      if (res && res.code == 200 && res.data && res.message == "SUCCESS") {
        emit("update:imgUrl", res.data.url);
      } else {
        getGlobalMessage("error", "文件上传失败！");
      }
    };

    return {
      handleUploaderSuccess,
      width,
      height,
      formatSize,
      imgUrl,
      action,
      accept,
      multiple,
    };
  },
});
</script>
<style lang="less" scoped>
.avatar-uploader {
  :deep(.el-upload) {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 178px;
    height: 178px;
  }
  :deep(.el-upload:hover) {
    border-color: #409eff;
  }
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
