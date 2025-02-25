<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
<!--VueRouter的容器组件，所有的页面都是放到这里-->
  <RouterView />
</template>

<!--scope：让其中的样式只会影响APP页面，不会影响.vue文件-->
<!--所有的样式都是放在这里面-->
<style scoped>

</style>
