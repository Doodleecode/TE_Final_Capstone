<template>
  <body>
    <div id="app">
      <div>
        <img class="logo" src="./assets/logo.png" />
      </div>
      <div id="nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'application' }">Application</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'pendings' }">Pendings</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{ name: 'pets' }">Pets</router-link>&nbsp;|&nbsp;
        
      </div>      
    </div>
    <div>
      <router-view />
    </div>
  </body>
</template>

<style>
body {
  margin-left: 100px;
  margin-right: 100px;
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
#nav{
  font-size: 30px;
  
}
body {
  background-color: white;
}
</style>
