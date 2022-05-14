import axios from 'axios';

const BASE_URL = 'http://localhost:5000';

const catApi = {

    async createCat(data) {
        const response = await axios.post(`${BASE_URL}/api/cat/`, data);
        return response.data;
    },
    async getCats() {
        const response = await axios.get(`${BASE_URL}/api/cat/`);
        return response.data;
    }
}
export default catApi;
