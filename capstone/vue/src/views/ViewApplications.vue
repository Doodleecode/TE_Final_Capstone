<template>
  <div>
    <h1 v-if="!isAdmin">You don't have access</h1>
    <div>

    </div>
      <h1>Pending Applications</h1>
      <application-list/>
  </div>
</template>

<script>
import ApplicationList from '../components/ApplicationList.vue'
export default {
  components: { ApplicationList },
  computed: {
    isAdmin() {
      return this.$store.getters.isAdmin;
    },
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

h1 {
  text-align: center;
}

</style>