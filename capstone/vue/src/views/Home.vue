<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <animal-filter type="All"/>
    <animal-filter type="dog"/>
    <animal-filter type="cat"/>
    <animal-list/>
  </div>
  
</template>

<script>
import AnimalFilter from '../components/AnimalFilter.vue';
import AnimalList from '../components/AnimalList.vue';
export default {
  components: { AnimalFilter, AnimalList},
  name: "home"
};
</script>
