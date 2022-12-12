<template>
  <div class="home">
    <div class="animal-filter" :class="$store.state.filter">
      <animal-filter type="All" />
      <animal-filter type="dog" />
      <animal-filter type="cat" />
    </div>
    <animal-list />
  </div>
</template>

<script>
import AnimalFilter from "../components/AnimalFilter.vue";
import AnimalList from "../components/AnimalList.vue";
export default {
  components: { AnimalFilter, AnimalList },
  name: "home",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");


.animal-filter {
  padding-left: 50px;
  display: flex;
  justify-content: space-evenly;
  cursor: pointer;
  font-size: 50px;
  display: block;
  text-align: center;
  font-family: "Oxygen", sans-serif;
  text-align: left;
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center center;
  background-color: rgb(122, 118, 118);
  border: solid 3px white;
  color: lightgray;
}


.animal-filter.All {
  background-image: url("../assets/cat_and_dog.webp");
}

.animal-filter.dog {
  background-image: url("../assets/dog.jpg");
}

.animal-filter.cat {
  background-image: url("../assets/cat.png");
}


</style>