<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <animal-filter :type="All"/>
    <animal-filter :type="Dog"/>
    <animal-filter :type="Cat"/>
    <animal-display/>
  </div>
  
</template>

<script>
import AnimalDisplay from '../components/AnimalDisplay.vue';
import AnimalFilter from '../components/AnimalFilter.vue';
export default {
  components: { AnimalFilter, AnimalDisplay },
  name: "home"
};
</script>
