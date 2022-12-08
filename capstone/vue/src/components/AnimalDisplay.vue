<template>
  <div class="animal-display">
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
.animal-display:hover {
  cursor: pointer;
  transform: scale(1.2);
  transition: all 0.2s ease-in-out;
  box-shadow: 0 0 6px black;
}
 img {
   max-width: 300px;
   max-height: 300px;   
 }
</style>