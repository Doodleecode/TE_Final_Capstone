<template>
  <div id="application">
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>
    <form v-on:submit.prevent="addApplication">
      <h1>Application</h1>
      <h2>Contact Info</h2>
      <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="contact.contactName" />
      </div>
      <div class="form-element">
        <label for="phone">Phone:</label>
        <input id="phone" type="tel" v-model="contact.phone" />
      </div>
      <div class="form-element">
        <label for="email">Email:</label>
        <input id="email" type="email" v-model="contact.email" />
      </div>
      <div class="form-element">
        <label for="city">City:</label>
        <input id="city" type="text" v-model="contact.city" />
      </div>
      <div class="form-element">
        <label for="state">State:</label>
        <select id="state" v-model.number="contact.state">
          <!-- https://www.freeformatter.com/usa-state-list-html-select.html -->
          <option value="AL">Alabama</option>
          <option value="AK">Alaska</option>
          <option value="AZ">Arizona</option>
          <option value="AR">Arkansas</option>
          <option value="CA">California</option>
          <option value="CO">Colorado</option>
          <option value="CT">Connecticut</option>
          <option value="DE">Delaware</option>
          <option value="DC">District Of Columbia</option>
          <option value="FL">Florida</option>
          <option value="GA">Georgia</option>
          <option value="HI">Hawaii</option>
          <option value="ID">Idaho</option>
          <option value="IL">Illinois</option>
          <option value="IN">Indiana</option>
          <option value="IA">Iowa</option>
          <option value="KS">Kansas</option>
          <option value="KY">Kentucky</option>
          <option value="LA">Louisiana</option>
          <option value="ME">Maine</option>
          <option value="MD">Maryland</option>
          <option value="MA">Massachusetts</option>
          <option value="MI">Michigan</option>
          <option value="MN">Minnesota</option>
          <option value="MS">Mississippi</option>
          <option value="MO">Missouri</option>
          <option value="MT">Montana</option>
          <option value="NE">Nebraska</option>
          <option value="NV">Nevada</option>
          <option value="NH">New Hampshire</option>
          <option value="NJ">New Jersey</option>
          <option value="NM">New Mexico</option>
          <option value="NY">New York</option>
          <option value="NC">North Carolina</option>
          <option value="ND">North Dakota</option>
          <option value="OH">Ohio</option>
          <option value="OK">Oklahoma</option>
          <option value="OR">Oregon</option>
          <option value="PA">Pennsylvania</option>
          <option value="RI">Rhode Island</option>
          <option value="SC">South Carolina</option>
          <option value="SD">South Dakota</option>
          <option value="TN">Tennessee</option>
          <option value="TX">Texas</option>
          <option value="UT">Utah</option>
          <option value="VT">Vermont</option>
          <option value="VA">Virginia</option>
          <option value="WA">Washington</option>
          <option value="WV">West Virginia</option>
          <option value="WI">Wisconsin</option>
          <option value="WY">Wyoming</option>
        </select>
      </div>
      <div class="form-element">
        <label for="age">Age:</label>
        <input id="age" type="number" v-model="contact.age" />
      </div>
      <div class="form-element">
        <label for="link">Social Link:</label>
        <input id="link" type="url" v-model="contact.socialLink" />
      </div>
      <h2>Volunteering Info</h2>
      <div class="form-element">
        <label for="hours">Weekly Hours:</label>
        <input
          id="hours"
          type="number"
          v-model="application.weeklyHours"
        />
      </div>
      <div class="form-element">
        <label for="time">Time of Day:</label>
        <select id="time" v-model.number="application.isDay">
          <option :value="true">Day(8am-5pm)</option>
          <option :value="false">Night(5pm-9pm)</option>
        </select>
      </div>
      <div class="form-element">
        <label for="animal">Preferred Animal:</label>
        <select id="animal" v-model.number="application.preferredAnimal">
          <option value="Dog">Dogs</option>
          <option value="Cat">Cats</option>
        </select>
      </div>
      <div class="form-element">
        <label for="reason">Why would you like to volunteer?</label>
        <textarea id="reason" v-model="application.reason"></textarea>
      </div>
      <div class="form-element">
      <button class="btn btn-submit">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
import applicationService from "../services/ApplicationService";
export default {
  name: "add-application",
  data() {
    return {
      errorMsg: "",
      contact: {
        contactName: "",
        phone: "",
        email: "",
        city: "",
        state: "",
        age: 18,
        socialLink: "",
      },
      application: {
        weeklyHours: 0,
        isDay: true,
        preferredAnimal: "",
        reason: "",
      },
    };
  },
  methods: {
    addApplication() {
      const applicationDTO = { contact: this.contact, application: this.application }
      applicationService
        .addApplication(applicationDTO)
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
      this.newContact = {};
    },
    goBack() {
      this.$router.push({ name: "home" });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " application. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " application. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " application. Request could not be created.";
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Oxygen&display=swap');


#application {
  display: block;
  text-align: center;
  font-family: 'Oxygen', sans-serif;
}

form {
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
  text-align: left;
  border: 3px solid #f1f1f1;
  position: relative;
  height: 100%;
  width: 600px;
  margin: auto;
  padding: 60px 60px;
  background-color: rgb(122, 118, 118);
}

h1 {
  text-transform: uppercase;
  font-weight: 150;
  padding-bottom: 40px;
  color: white;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  text-align: center;
}

h2 {
  color: lightgray;
}

.form-element label {
  color: lightgray;
}

option {
  color: black;
}

.form-element input, select, textarea {
  width: 89%;
  padding: 10px 20px;
  display: block;
  height: 15px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.1);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  color: white;
} 

.form-element select {
  width: 96.5%;
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

/* .form-element {
  width: 700px;
  padding-bottom: 10px;
  display: flex;
}
.form-element label {
  width: 100px;
  vertical-align: top;
}
.form-element input,
select,
textarea, .btn {
  width: 400px;
  font-size: 1.1rem;
  flex: 1;
}

textarea {
  display: block;
}
.form-element textarea {
  height: 150px;
}
.actions {
  float: right;
}
.actions button {
  margin-left: 10px;
} */
</style>