<template>
  <div class="contact" :key="contact.id">
    <div class="centered">
      <h3>{{ contact.contactName }}, {{ contact.age }}</h3>
    </div>
    <div class="info">
      <p>Phone: {{ contact.phone }}</p>
      <p>Email: {{ contact.email }}</p>
      <p>City: {{ contact.city }}</p>
      <p>State: {{ contact.state }}</p>
      <p>SocialLink: {{ contact.socialLink }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["contact"],
};
</script>

<style scoped>

h3 {
  color: white;
}
</style>