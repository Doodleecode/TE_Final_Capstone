<template>
  <div class="well" :class="{ filtered : $store.state.filter == type }">
    <span v-on:click="updateFilter()">{{ type }}</span>
  </div>
</template>

<script>
export default {
    props: ["type"],
    methods: {
    updateFilter() {
      this.$store.commit('UPDATE_FILTER', this.type);
    }
  },
}
</script>

<style>
.filtered {
  background-color: lightgreen;
}
</style>