<template>
<div class="main">
  <div class="applications">
    <div class="loading" v-if="isLoading">
        <img src="../assets/cat_loading.gif" />
      </div>
    <application-display v-for="application in applications" v-bind:key="application.application.id"
        :application="application" v-else/>
  </div>
</div>

  
</template>

<script>
import ApplicationDisplay from './ApplicationDisplay.vue';
import applicationService from "../services/ApplicationService";

export default {
  components: {
    ApplicationDisplay
  },
  data() {
    return {
      applications: [],
      isLoading: true,
    }
  },
  created() {
    applicationService.getPending().then((response) => {
      this.applications = response.data;
      this.isLoading = false;
    });
  },
};
</script>

<style>
div.main {
  margin: 1rem 0;
}
div.main div.well-display {
  display: flex;
  justify-content: space-around;
}

div.main div.well-display div.well {
  display: inline-block;
  width: 15%;
  border: 1px black solid;
  border-radius: 6px;
  text-align: center;
  margin: 0.25rem;
}

div.main div.well-display div.well span.amount {
  color: darkslategray;
  display: block;
  font-size: 2.5rem;
}

div.main div.well-display div.well {
  cursor: pointer;
}
</style>
