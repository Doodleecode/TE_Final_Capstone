<template>
  <div class="well" :class="[{ filtered: $store.state.filter == type }, type]">
    <span v-on:click="updateFilter()">{{ type }}</span>
  </div>
</template>

<script>
export default {
  props: ["type"],
  methods: {
    updateFilter() {
      this.$store.commit("UPDATE_FILTER", this.type);
    },
  },
};
</script>

<style>
.well {
  display: block;
  text-transform: uppercase;
  text-align: center;
  width: 150px;
  margin-top: 5px;
  margin-bottom: 5px;
  border: 5px solid white;

}
.well:hover{
  letter-spacing: 5px;
  border-top: 5px solid rgb(0, 64, 184);
  border-bottom: 5px solid rgb(0, 64, 184);  
  transition: all 0.3s ease-in-out;
}

.filtered{
  letter-spacing: 5px;
  border-top: 5px solid rgb(0, 64, 184);
  border-bottom: 5px solid rgb(0, 64, 184);  
  transition: all 0.3s ease-in-out;
}

</style>