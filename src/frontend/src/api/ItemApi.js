import axios from 'axios';

const BASE_URL = 'http://localhost:5000';

const itemApi = {

    async createItem(data) {
        const response = await axios.post(`${BASE_URL}/api/item/`, data);
        return response.data;
    },
    async getItems() {
        const response = await axios.get(`${BASE_URL}/api/item/`);
        return response.data;
    },
    async deleteItem(id) {
        const response = await axios.delete(`${BASE_URL}/api/item/${id}`);
        return response.data;
    },
    async getItem(id) {
        const response = await axios.get(`${BASE_URL}/api/item/${id}`);
        return response.data;
    },
    async updateItem(id, data) {
        const response = await axios.put(`${BASE_URL}/api/item/${id}`, data);
        return response.data;
    }
}
export default itemApi;
