import axios from 'axios';

const BASE_URL = 'http://localhost:5000';

const doseApi = {

    async createDose(data) {
        const response = await axios.post(`${BASE_URL}/api/dose/`, data);
        return response.data;
    },
    async getDosesByCat(catId) {
        const response = await axios.get(`${BASE_URL}/api/dose/cat/${catId}`);
        return response.data;
    },
    async getDosesByDate(date) {
        const response = await axios.get(`${BASE_URL}/api/dose/date/${date}`);
        return response.data;
    },
    async deleteDose(id) {
        const response = await axios.delete(`${BASE_URL}/api/dose/${id}`);
        return response.data;
    },
    async getDose(id) {
        const response = await axios.get(`${BASE_URL}/api/dose/${id}`);
        return response.data;
    },
    async updateDose(id, data) {
        const response = await axios.put(`${BASE_URL}/api/dose/${id}`, data);
        return response.data;
    }
}
export default doseApi;
