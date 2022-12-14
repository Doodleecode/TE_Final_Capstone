<template>
<div>
  <h1 v-if="!isVolunteer">You don't have access</h1>
  <div>
    <h1>Directory</h1>
    <contact-list/>
  </div>
</div>
  
</template>

<script>
import ContactList from '../components/ContactList.vue';
export default {
  components: { ContactList },
  computed: {
    isVolunteer() {
      return this.$store.getters.isVolunteer;
    },
  }
}
</script>

<style scoped>

h1 {
  text-align: center;
  font-family: 'Oxygen', sans-serif;
  color:  rgb(65, 64, 64);
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
}

</style>