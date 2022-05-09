import { api } from './api';
import { EndPoints } from './endpoints';

export const logIn = () => {
	api.post(EndPoints.LOG_IN, {});
};

export const logOut = () => {
	api.post(EndPoints.LOG_OUT, {});
};

export const signUp = () => {
	api.post(EndPoints.SIGN_UP, {});
};
