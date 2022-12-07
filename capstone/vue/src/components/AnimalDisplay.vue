<template>
  <div>
    <h3>{{ animal.name }}</h3>
    <p>{{ animal.age }}</p>
    <img :src="animal.imageLink"/>
  </div>
</template>

<script>
export default {
  props: ["animal"]
};
</script>

<style scoped>
 img {
   max-width: 200px;
   height: auto;
 }
</style>