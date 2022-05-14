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
    },
    async deleteCat(id) {
        const response = await axios.delete(`${BASE_URL}/api/cat/${id}`);
        return response.data;
    },
    async getCat(id) {
        const response = await axios.get(`${BASE_URL}/api/cat/${id}`);
        return response.data;
    },
    async updateCat(id, data) {
        const response = await axios.put(`${BASE_URL}/api/cat/${id}`, data);
        return response.data;
    }
}
export default catApi;
