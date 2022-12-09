import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000",
});

export default {

  addApplication(application) {
    return http.post('/submit', application);
  },

  getPending() {
    return http.get('applications/P');
  }

}
