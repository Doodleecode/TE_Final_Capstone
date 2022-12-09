<template>
  <div id="animal">
    <div class="status-message error" v-show="errorMsg !== ''">
      {{ errorMsg }}
    </div>
    <form v-on:submit.prevent="addAnimal">
      <h1 id="animal-title">New Animal</h1>
      <div>
        <div>
          <label for="name">Name:</label>
          <input type="text" v-model="pet.name" />
        </div>
        <div>
          <label for="Type">Type:</label>
          <input type="text" v-model="pet.type" />
        </div>
        <div>
          <label for="age">Age:</label>
          <input type="number" v-model="pet.age" />
        </div>
        <div>
          <label for="sex">Sex:</label>
          <input type="text" v-model="pet.sex" />
        </div>
        <div>
          <label for="breed">Breed:</label>
          <input type="text" v-model="pet.breed" />
        </div>
        <div>
          <label for="size">Size:</label>
          <input type="text" v-model="pet.size" />
        </div>
        <div>
          <label for="isGoodWithAnimals">Good with Animal?</label>
          <input type="text" v-model="pet.goodWithAnimals" />
        </div>
        <div>
          <label for="isGoodWithKids">Good with Kid?</label>
          <input type="text" v-model="pet.goodWithKids" />
        </div>
        <div>
          <label for="imageLink">Image Link:</label>
          <input type="text" v-model="pet.imageLink" />
        </div>
      </div>
      <div id="submit" class="form-element">
        <button class="btn btn-submit">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
// import AnimalForm from "../components/AnimalForm.vue";
import animalService from "../services/AnimalService";

export default {
  name: "add-animal",
  data() {
    return {
      errorMsg: "",
      pet: {
        name: "",
        type: "",
        age: 0,
        sex: "",
        breed: "",
        size: "",
        imageLink: "",
        goodWithAnimals: true,
        goodWithKids: true,
        available: true,
      },
    };
  },
  methods: {
    addAnimal() {
      const pet =  this.pet ;
      animalService
        .addAnimal(pet)
        .then((response) => {
          if (response.status === 201) {
            this.goBack();
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
    resetForm() {
      this.newPet = {};
    },
    goBack() {
      this.$router.push({ name: "home" });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " pet. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg = "Error " + verb + " pet. Server could not be reached.";
      } else {
        this.errorMsg = "Error " + verb + " pet. Request could not be created.";
      }
    },
  },
};
</script>
