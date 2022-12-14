import axios from 'axios';

export default {

  getAnimals() {
    return axios.get('/pets');
  },

  getAnimal(petID) {
    return axios.get(`/pets/${petID}`)
  },

  addAnimal(pet) {
    return axios.post('/pets', pet);
  }

}
