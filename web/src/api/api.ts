import axios, { AxiosRequestConfig } from 'axios';

const API_URL = 'http://localhost:8081/api/';

export const api = axios.create({
	baseURL: API_URL,
	withCredentials: true,
});

api.interceptors.request.use((config: AxiosRequestConfig) => {
	if (config && config.headers) {
		config.headers.Authorization = `Bearer ${localStorage.getItem('token')}`;
	}

	return config;
});

api.interceptors.response.use(undefined, (error) => {
	return error;
});
