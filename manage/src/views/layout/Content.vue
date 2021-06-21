<template>
  <PageView> </PageView>
</template>

<script lang="ts">
import PageView from '../../components/PageView.vue'
import { defineComponent } from "vue";

export default defineComponent({
  name: "ContentComp",
  components:{
    PageView
  }
});
</script>

<style lang="less" scoped></style>
