<template>
  <div class="contact" :key="contact.id">
    <div class="centered">
      <h3>{{ contact.contact.contactName }}, {{ contact.contact.age }}, {{ userRole }}</h3>
    </div>
    <div class="info">
      <p>UserNumber: {{ contact.user.id }}</p>
      <p>Phone: {{ contact.contact.phone }}</p>
      <p>Email: {{ contact.contact.email }}</p>
      <p>City: {{ contact.contact.city }}</p>
      <p>State: {{ contact.contact.state }}</p>
      <p>SocialLink: {{ contact.contact.socialLink }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["contact"],
  computed: {
    userRole() {
      if (this.contact.user.authorities[0].name === "ROLE_VOLUNTEER") {
        return "Volunteer";
      } else {
        return "Admin";
      }
    },
  }
};
</script>

<style scoped>

h3 {
  color: white;
}

.info {
  text-decoration: none;
}
</style>