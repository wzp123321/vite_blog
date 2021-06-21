<template>
  <!-- <transition name="slide">
    <router-view></router-view>
  </transition> -->
  <router-view v-slot="{ Component }">
    <transition name="slide">
      <component :is="Component" />
    </transition>
  </router-view>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  name: "PageView",
  components: {},
});
</script>

<style lang="less" scoped>
.slide-enter-active {
  transition: all 0.4s linear;
}
.slide-leave-active {
  transition: all 0.4s linear;
}
.slide-enter {
  transform: translateX(-100%);
  opacity: 0;
}
.slide-leave-to {
  transform: translateX(110%);
  opacity: 0;
}
</style>
