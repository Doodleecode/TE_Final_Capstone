<template>
  <body>
    <div id="app">
      <div>
        <img class="logo" src="./assets/RSPH.png" />
      </div>
      <div id="nav" class="flex-display">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'application' }">Application</router-link>
        <router-link v-bind:to="{ name: 'login' }">Login</router-link>
        <router-link v-bind:to="{ name: 'pendings' }">Pending Applications</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{ name: 'pets' }">Add Pet</router-link>  
      </div>      
    </div>
    <div>
      <router-view />
    </div>
  </body>
</template>


<style scoped>
#nav > a {
  border-right: 2px solid black;
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