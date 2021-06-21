<template>
  <router-view></router-view>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  name: "App",
  components: {},
  mounted() {
    console.log();
  },
});
</script>
<style>
#app {
  height: 100%;
  background: #ccc;
}
</style>
