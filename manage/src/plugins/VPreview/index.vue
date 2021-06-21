<template>
  <div class="image-preview-wrapper" @mousewheel="onMouseScroll">
    <img
      @dragstart="onDragStart"
      @dragend="onDragEnd"
      @drag="onDrag"
      :src="url"
      alt=""
      :style="`transform:scale(${scale}) translate(calc(-50%${
        x > 0 ? ' +' : ' -'
      } ${x > 0 ? x : -x}px),calc(-50%${y > 0 ? ' +' : ' -'} ${
        y > 0 ? y : -y
      }px) )`"
    />
    <i class="iconfont iconguanbi" @click="close"></i>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import { useWebCommonSetting } from "../../hooks/common/useWebCommonSetting";

export default defineComponent({
  props: {
    /**
     * 图片地址
     */
    url: {
      type: String,
      default: "",
    },
  },
  setup(props) {
    const { url } = props;
    const { throttle } = useWebCommonSetting();
    const previewState = reactive({
      scale: 1,
      isDown: false,
      _x: 0,
      x: 0,
      _y: 0,
      y: 0,
    });
    const maxScale = 2;
    const minScale = 0.5;
    /**
     * 关闭
     */
    const close = () => {
      const modelDom = document.body.querySelector(".image-preview");
      modelDom && document.body.removeChild(modelDom);
    };
    /**
     * 滚轮滚动
     */
    const onMouseScroll = (e) => {
      throttle(() => {
        if (e && e.wheelDelta > 0) {
          if (previewState.scale < maxScale) {
            previewState.scale += 0.1;
          }
        } else {
          console.log("向下滚动");
          if (previewState.scale > minScale) {
            previewState.scale -= 0.1;
          }
        }
      }, 200);
    };

    const onDragStart = (e) => {
      console.log(e);
      previewState.isDown = true;
      previewState._x = e.clientX;
      previewState._y = e.clientY;
    };
    const onDragEnd = (e) => {
      if (previewState.isDown) {
        previewState.x = e.clientX - previewState._x;
        previewState.y = e.clientY - previewState._y;
      }
      previewState.isDown = false;
    };
    const onDrag = (e) => {
      throttle(() => {
        if (previewState.isDown) {
          console.log(e.clientX, e.clientY);
          previewState.x = e.clientX - previewState._x;
          previewState.y = e.clientY - previewState._y;
          console.log(
            ` translate(calc(-50%${previewState.x > 0 ? " +" : " -"} ${
              previewState.x > 0 ? previewState.x : -previewState.x
            }px),calc(-50%${previewState.y > 0 ? " +" : " -"} ${
              previewState.y > 0 ? previewState.y : -previewState.y
            }px) )`
          );
        }
      }, 80);
    };

    return {
      ...toRefs(previewState),
      close,
      url,
      onMouseScroll,
      onDragStart,
      onDrag,
      onDragEnd,
    };
  },
});
</script>
<style lang="less" scoped>
.image-preview-wrapper {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 9999;
  transform: all 500ms;
  -webkit-user-select: none;
  -moz-user-select: none;
  -o-user-select: none;
  user-select: none;
  img {
    width: 40%;
    height: auto;
    position: absolute;
    top: 50%;
    left: 50%;
    transform-origin: left top;
    cursor: grab;
    -webkit-user-select: none;
    -moz-user-select: none;
    -o-user-select: none;
    user-select: none;
  }
  .iconfont {
    cursor: pointer;
    position: fixed;
    top: 30px;
    right: 30px;
    font-size: 24px;
    color: #fff;
  }
  .iconfont:hover {
    transform: scale(1.1);
    transition: all 300ms;
    opacity: 0.8;
  }
  img:foucs {
    cursor: pointer;
  }
}
</style>
