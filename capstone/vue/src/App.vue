<template>
  <body>
    <div id="app">
      <div>
        <img class="logo" src="./assets/RSPH.png" />
      </div>
      <div id="nav" class="flex-display">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{ name: 'application' }" v-if="isUser">Application</router-link>
        <router-link v-bind:to="{ name: 'pets' }" v-if="isVolunteer">Add Pet</router-link>  
        <router-link v-bind:to="{ name: 'directory' }" v-if="isVolunteer">Directory</router-link>
        <router-link v-bind:to="{ name: 'pendings' }" v-if="isAdmin">Pending Applications</router-link>
      </div>      
    </div>
    <div>
      <router-view />
    </div>
  </body>
</template>

<script>
export default {
  computed: {
    isAdmin() {
      return this.$store.getters.isAdmin;
    },
    isVolunteer() {
      return this.$store.getters.isVolunteer;
    },
    isUser() {
      return this.$store.getters.isUser;
    }
  }
}
</script>

<style scoped>
#nav > a {
  border-right: 2px solid rgb(65, 64, 64);
  padding: 10px;
}

#nav > a:last-child {
  border: 0px;
}
</style>

<style>

@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");
* {
  box-sizing: border-box;
}
.flex-display {
  display: flex;
  align-items: center;
}

body {
  background-color: rgb(180, 219, 245);
}

#app {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}

.logo {  
  max-width: 256px;
}

a {
  font-family: 'oxygen';
  color: rgb(65, 64, 64);
  text-decoration: none;
}
a:hover {
  font-size: 35px;
  text-decoration-line: underline;
  text-decoration-color: cornflowerblue;
}

#nav{
  font-size: 30px;
}

</style>