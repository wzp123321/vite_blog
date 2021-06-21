<template>
  <div class="container flex-column-justify-end" style="min-height: 100%">
    <HeaderBox></HeaderBox>
    <ContentBox></ContentBox>
    <FooterBox></FooterBox>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import ContentBox from "./ContentBox.vue";
import HeaderBox from "./header/index.vue";
import FooterBox from "./FooterBox.vue";
import { useWebCommonSetting } from "/@/hooks/common/useWebCommonSetting";

export default defineComponent({
  name: "App",
  components: {
    ContentBox,
    HeaderBox,
    FooterBox,
  },
  setup() {
    const { throttle } = useWebCommonSetting();
    window.addEventListener("scroll", function (e) {
      throttle(() => {
        console.log(e);
      }, 200);
    });
  },
});
</script>
