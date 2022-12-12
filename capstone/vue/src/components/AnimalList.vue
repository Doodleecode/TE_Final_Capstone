<template>
  <div>
    <h1 id="title">Adoptable Animals</h1>
    <div class="animals">
      <div class="loading" v-if="isLoading">
        <img src="../assets/cat_loading.gif" />
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
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

#title {
  text-align: center;
}

h1 {
  font-family: "Oxygen", sans-serif;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  color: rgb(77, 76, 76);
}
h3, p {
  font-family: "Oxygen", sans-serif;
  color: lightgray;
}
.animals {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}
.animal {
  background-color: #f7fafc;
  border-radius: 10px;
  margin: 20px;
  padding: 2%;  
  
  text-align: center;
  /* max-width: 20%;
  max-height: 32.36%; */
}
.loading {
  flex: 3;
}
.animal a:link,
.animal a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}

.loading {
  text-align: center;
  
}
</style>