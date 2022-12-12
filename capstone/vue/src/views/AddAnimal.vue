<template>
  <div id="animal">
    <div class="status-message error" v-show="errorMsg !== ''">
      {{ errorMsg }}
    </div>
    <form v-on:submit.prevent="addAnimal">
      <h1 id="animal-title">Add an Adoptable Pet</h1>
      <div>
        <div class="form-element">
          <label for="name">Name:</label>
          <input type="text" v-model="pet.name" required/>
        </div><br><br>
        <div class="form-element">
          <label for="Type">Type:</label>
          <input type="text" v-model="pet.type" required/>
        </div><br><br>
        <div class="form-element">
          <label for="age">Age:</label>
          <input type="number" v-model="pet.age" required/>
        </div><br><br>
        <div class="form-element">
          <label for="sex">Sex:</label>
          <input type="text" v-model="pet.sex"  required/>
        </div><br><br>
        <div class="form-element">
          <label for="breed">Breed:</label>
          <input type="text" v-model="pet.breed" />
        </div><br><br>
        <div class="form-element">
          <label for="size">Size (S/M/L/XL/XXL):</label>
          <input type="text" v-model="pet.size" />
        </div><br><br>
        <div class="good-with">
            <label>Good with:</label>
          
          <div class="form-element">
            <label for="isGoodWithDogs" class="required">Dogs?</label>
            <select id="isGoodWithDogs" v-model.number="pet.goodWithDogs" required>
              <option :value="true">✅</option>
              <option :value="false">❌</option>
            </select>
          </div>
          <div class="form-element">
            <label for="isGoodWithCats" class="required">Cats?</label>
            <select id="isGoodWithCats" v-model.number="pet.goodWithCats" required>
              <option :value="true">✅</option>
              <option :value="false">❌</option>
            </select>
          </div>
          <div class="form-element">
            <label for="isGoodWithKids" class="required">Kids?</label>
            <select id="isGoodWithKids" v-model.number="pet.goodWithKids" required>
              <option :value="true">✅</option>
              <option :value="false">❌</option>
            </select>
          </div>
        </div><br><br>

        <div class="form-element">
          <label for="imageLink">Image Link:</label>
          <input type="text" v-model="pet.imageLink" required/>
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
        goodWithDogs: null,
        goodWithCats: null,
        goodWithKids: null,
        available: true,
      },
    };
  },
  methods: {
    addAnimal() {
      const pet = this.pet;
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

.good-with {
  width: 93.5%;
  height: 100%;
  display: flex;
  justify-items: space-between;
  align-items: baseline;
}

.good-with > div {
  flex: 1;
  
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
label {
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
  height: 21px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.1);
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
  width: 93%;
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