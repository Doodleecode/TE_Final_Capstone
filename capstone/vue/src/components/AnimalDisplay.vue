<template>
  <div class="flex-display">
    <h3 class='animal-name'>{{ animal.name }}</h3>
    <p>{{ animal.age }}</p>
    <div class="animal-image"><img :src="animal.imageLink" /></div>
    <router-link class="btn updateAnimal" :to="{ name: 'Edit', params: {petID: $route.params.petID} }">
    <button v-if="isAdmin">Update</button>
    </router-link>
  </div>
</template>

<script>
export default {
  props: ["animal"],
  computed: {
    isAdmin() {
      return this.$store.getters.isAdmin;
    },
  }
};
</script>

<style scoped>

.flex-display {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  background-color: rgb(122, 118, 118);
  border: 3px solid white;
  width: 10vw;
  flex-grow: 1;
}

.flex-display:hover {
  cursor: pointer;
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