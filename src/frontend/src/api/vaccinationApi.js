import axios from 'axios';

const BASE_URL = 'http://localhost:5000';

const vaccinationApi = {

    async createVaccination(data) {
        const response = await axios.post(`${BASE_URL}/api/vaccination/`, data);
        return response.data;
    },
    async getVaccinations() {
        const response = await axios.get(`${BASE_URL}/api/vaccination/`);
        return response.data;
    },
    async deleteVaccination(id) {
        const response = await axios.delete(`${BASE_URL}/api/vaccination/${id}`);
        return response.data;
    },
    async getVaccination(id) {
        const response = await axios.get(`${BASE_URL}/api/vaccination/${id}`);
        return response.data;
    },
    async updateVaccination(id, data) {
        const response = await axios.put(`${BASE_URL}/api/vaccination/${id}`, data);
        return response.data;
    }
}
export default vaccinationApi;
