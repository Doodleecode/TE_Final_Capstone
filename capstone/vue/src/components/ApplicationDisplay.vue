<template>
  <div class="application" v-bind:key="application.application.id" v-if="!isHidden">
    <div class="centered">
      <h3>{{ application.contact.contactName }}, {{ application.contact.age }}</h3>
    </div>
    <div class="info">
      <p>Hours: {{ application.application.weeklyHours }}</p>
      <div>
        <p v-if="application.application.isDay">Time: Day</p>
        <p v-else>Time: Night</p>
      </div>
      <p>Animal: {{ application.application.preferredAnimal }}</p>
    </div>
    <div class="centered">
      <p>{{ application.application.reason }}</p>
    </div>
    
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
.centered {
  text-align: center;
}

div.info {
  width: 100%;
  display: flex;
  align-content: space-between;
}
div.info > * {
  flex: 1;
  text-align: center;

}

div.application {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
  width: 100%;
  background-color: white;
}

div.application p {
  margin: 20px;
}

div.application h3 {
  display: inline-block;
}
</style>
