<template>
<div class="main">
  <div class="applications">
    <div class="loading" v-if="isLoading">
        <img src="../assets/cat_loading.gif" />
      </div>
      <h1 v-if="pendingApps">No applications to review</h1>
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
  computed: {
    pendingApps() {
      return this.applications.length === 0;
    }
  },
  created() {
    this.refreshApplications();
  },
  methods: {
    refreshApplications() {
      applicationService.getPending().then((response) => {
      this.applications = response.data;
      this.isLoading = false;
    });
    }
    
  }
};
</script>

<style scoped>

div.main {
  margin: 0rem 0;
}

div.main div.well-display {
  display: flex;
  justify-content: space-around;
}

div.main div.well-display div.well {
  display: inline-block;
  width: 15%;
  border: 3px white solid;
  text-align: center;
  margin: 0.25rem;
}

div.main div.well-display div.well span.amount {
  color: rgb(65, 64, 64);
  display: block;
  font-size: 2.5rem;
}

div.main div.well-display div.well {
  cursor: pointer;
}
</style>
