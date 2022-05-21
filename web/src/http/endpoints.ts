// export const BASE_URL = 'http://localhost:5000/api';
export const BASE_URL = 'http://10.178.130.62/api/';

export enum EndPoints {
	LOG_IN = 'auth/signin',
	LOG_OUT = 'auth/logout',
	SIGN_UP = 'auth/signup',
	REFRESH_TOKEN = 'auth/refreshToken',
	GET_CURRENT_USER = 'currentUser',
	GET_USER = '/get_user',
}
