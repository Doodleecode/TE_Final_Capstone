import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000",
});

export default {

  getAnimals() {
    return http.get('/animals');
  },

  getAnimal(petID) {
    return http.get(`/animals/${petID}`)
  }

}
