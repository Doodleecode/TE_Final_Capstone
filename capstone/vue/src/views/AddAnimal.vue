<template>
  <div id="animal">
    <div class="status-message error" v-show="errorMsg !== ''">
      {{ errorMsg }}
    </div>
    <form v-on:submit.prevent="addAnimal">
      <h1 id="animal-title">Add Animal</h1>
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
          <label for="size">Weight:</label>
          <input type="text" v-model="pet.size" />
        </div>
        <div>
          <label for="isGoodWithAnimals">Good with Dogs?</label>
          <input type="text" v-model="pet.goodWithAnimals" />
        </div>
        <div>
          <label for="isGoodWithAnimals">Good with Cats?</label>
          <input type="text" v-model="pet.goodWithAnimals" />
        </div>
        <div>
          <label for="isGoodWithKids">Good with Kids?</label>
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

<style>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

#application {
  display: block;
  text-align: center;
  font-family: "Oxygen", sans-serif;
}

form {
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
  text-align: left;
  border: 3px solid #f1f1f1;
  position: relative;
  height: 100%;
  width: 100%;
  margin: auto;
  padding: 60px 60px;
  background-color: rgb(122, 118, 118);
}

#animal-title {
  text-transform: uppercase;
  font-weight: 150;
  padding-bottom: 40px;
  color: white;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  font-family: "Oxygen", sans-serif;
  /* text-align: center; */
}

h2 {
  color: lightgray;
}
label{
  font-family: "Oxygen", sans-serif;
  font-size: 25px;
  color: lightgray;
  padding: 10px;
}
.form-element input,
textarea {
  width: 89%;
  padding: 10px 20px;
  display: block;
  height: 15px;
  border-radius: 10px;
  background-color: rgba(240, 9, 9, 0.89);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  color: white;
}

select {
  width: 96.5%;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 10px 20px;
  display: block;
  height: 45px;
  border-radius: 10px;
  color: white;
  margin-top: 15px;
}

option {
  color: black;
}

.form-element textarea {
  height: 120px;
}

button {
  background-color: cornflowerblue;
  color: white;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 39px;
  border-radius: 10px;
  margin-top: 30px;
  border: none;
  text-transform: uppercase;
}

button:hover {
  opacity: 0.8;
}
</style>