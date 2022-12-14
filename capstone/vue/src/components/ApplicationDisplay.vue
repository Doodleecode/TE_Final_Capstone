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
    
    <div class="flex-display">
      <button @click="onApprove()">Approve</button>
      <button @click="onDeny()">Deny</button>
    </div>
  </div>
</template>

<script>
import applicationService from "../services/ApplicationService";
export default {
  props: ["application"],
  data() {
    return {
      isHidden: false
    }
    
  },
  methods: {
    onApprove() {
      applicationService.putApprove(this.application.application.id).then(() => {
        this.isHidden = true;
    });
      
    },
    onDeny() {
      applicationService.putDeny(this.application.application.id).then(() => {
        this.isHidden = true;
    });
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

div {
  background-color: rgb(122, 118, 118);
}
div.info {
  width: 100%;
  display: flex;
  /* align-content: space-between; */
}
div.info > * {
  flex: 1;
  text-align: center;
}

div.application {
  border: 3px white solid;
  padding: 1rem;
  width: 100%;
  display: block;
  text-align: center;
  font-family: "Oxygen", sans-serif;
}

div.application p {
  margin: 20px;
}

div.application h3 {
  display: inline-block;
}

button {
  background-color: cornflowerblue;
  color: white;
  width: 50%;
  padding: 10px 20px;
  display: block;
  height: 39px;
  border-radius: 10px;
  margin-top: 30px;
  border: solid 1px white;
  text-transform: uppercase;
}
</style>
