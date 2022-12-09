<template>
  <div class="application" v-bind:key="application.application.id" v-if="!isHidden">
    <h3>{{ application.contact.contactName }}, {{ application.contact.age }}</h3>
    <div class="info">
      <p>Hours: {{ application.application.weeklyHours }}</p>
      <div>
        <p v-if="application.application.isDay">Time: Day</p>
        <p v-else>Time: Night</p>
      </div>
      <p>Animal: {{ application.application.preferredAnimal }}</p>
    </div>
    <p>{{ application.application.reason }}</p>
    <div>
      <button @click="onApprove()">Approve</button>
      <button @click="onDeny()">Deny</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ["application"],
  data() {
    return {
      isHidden: false
    }
    
  },
  methods: {
    onApprove() {
      //some application service to approve then
      this.isHidden = true;
    },
    onDeny() {
      this.isHidden = true;
    }
  }
};
</script>

<style scoped>
div.info {
  display: flex;
  align-content: space-between;
}

div.main div.application {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
}

div.main div.application p {
  margin: 20px;
}

div.main div.application h3 {
  display: inline-block;
}
</style>
