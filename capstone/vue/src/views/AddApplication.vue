<template>
  <div>
    <h1>Application</h1>
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>
    <form v-on:submit.prevent="addApplication">
      <h2>Contact Info</h2>
      <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="newContact.name" />
      </div>
      <div class="form-element">
        <label for="phone">Phone:</label>
        <input id="phone" type="tel" v-model="newContact.phone" />
      </div>
      <div class="form-element">
        <label for="email">Email:</label>
        <input id="email" type="email" v-model="newContact.email" />
      </div>
      <div class="form-element">
        <label for="city">City:</label>
        <input id="city" type="text" v-model="newContact.city" />
      </div>
      <div class="form-element">
        <label for="state">State:</label>
        <select id="state" v-model.number="newContact.state">
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
        <label for="link">Social Link:</label>
        <input id="link" type="url" v-model="newContact.link" />
      </div>
      <h2>Volunteering Info</h2>
      <div class="form-element">
        <label for="hours">Weekly Hours:</label>
        <input
          id="hours"
          type="number"
          v-model="newApplicationInfo.weeklyHours"
        />
      </div>
      <div class="form-element">
        <label for="time">Time of Day:</label>
        <select id="time" v-model.number="newApplicationInfo.timeOfDay">
          <option value="DAY">Day(8am-5pm)</option>
          <option value="NIGHT">Night(5pm-9pm)</option>
        </select>
      </div>
      <div class="form-element">
        <label for="animal">Preferred Animal:</label>
        <select id="animal" v-model.number="newApplicationInfo.animal">
          <option value="DOG">Dogs</option>
          <option value="CAT">Cats</option>
        </select>
      </div>
      <div class="form-element">
        <label for="why">Why would you like to volunteer?</label>
        <textarea id="why" v-model="newApplicationInfo.why"></textarea>
      </div>
      <button class="btn btn-submit">Submit</button>
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
      newContact: {
        name: "",
        phone: "",
        email: "",
        city: "",
        state: "",
        link: "",
      },
      newApplicationInfo: {
        weeklyHours: 0,
        timeOfDay: "",
        animal: "",
        why: "",
      },
    };
  },
  methods: {
    addApplication() {
      const application = new Array(this.newContact, this.newApplicationInfo);
      applicationService
        .addApplication(application)
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

<style>
form {
  width: 700px;
  margin: 20px;
  
}
.form-element {
  width: 700px;
  padding-bottom: 10px;
  display: block;
}
.form-element label {
  width: 100px;
  vertical-align: top;
}
.form-element input,
select,
textarea {
  width: 400px;
  font-size: 1.1rem;
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
}
</style>