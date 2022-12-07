<template>
  <div>
    <h1>Adoptable Animals</h1>
    <div class="animals">
      <div class="loading" v-if="isLoading">
        <img src="../assets/ping_pong_loader.gif" />
      </div>
      <animal-display
        class="animal"
        v-for="animal in filteredAnimals"
        v-bind:key="animal.id"
        :animal="animal"
        v-else
      />
    </div>
  </div>
</template>

<script>
import animalService from "../services/AnimalService";
import AnimalDisplay from "./AnimalDisplay";
export default {
  components: {
    AnimalDisplay,
  },
  data() {
    return {
      animals: [],
      isLoading: true,
    };
  },
  created() {
    animalService.getAnimals().then((response) => {
      this.animals = response.data;
      this.isLoading = false;
    });
  },
  computed: {
    filteredAnimals() {
      const animalsFilter = this.$store.state.filter;
      return this.animals.filter((animal) => {
        return animalsFilter === "All" ? true : animalsFilter === animal.type;
      });
    },
  },
};
</script>

<style>
.animals {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.animal {
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 0 20px;
  text-align: center;
}
.loading {
  flex: 3;
}
.animal a:link,
.animal a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}
</style>