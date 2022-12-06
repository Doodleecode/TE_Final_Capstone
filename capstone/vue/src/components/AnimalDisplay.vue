<template>
  <div>
    <h3>{{ animal.name }}</h3>
    <p>{{ animal.age }}</p>
    <img v-bind:src="animal.url"/>
  </div>
</template>

<script>
export default {
  props: ["animal"]
};

</script>