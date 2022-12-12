<template>
  <div class="animal-display">
    <h3 class='animal-name'>{{ animal.name }}</h3>
    <p>{{ animal.age }}</p>
    <div class="animal-image"><img :src="animal.imageLink" /></div>
  </div>
</template>

<script>
export default {
  props: ["animal"],
};
</script>

<style scoped>
.animal-display {
  background-color: rgb(122, 118, 118);
  width: 10rem;
  HEIGHT: 16.18rem;
  border: 3px solid cornflowerblue;
}

.animal-display:hover {
  cursor: pointer;
  /* transform: scale(1.2) */
  transition: all 0.2s ease-in-out;
  box-shadow: 0 0 20px black;
}
.animal-image {
  height: 10rem;
  
}
img {
  max-height: 100%;
  max-width: 100%;
  object-fit: scale-down;
}
.animal-name {
  text-transform: capitalize;
}
</style>