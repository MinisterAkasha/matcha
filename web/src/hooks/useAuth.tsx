import { useCallback } from 'react';
import { useLogInMutation, useLogOutMutation, useSignUpMutation } from '../services/AuthService';
import { useAppDispatch } from './storeHooks';
import { logout } from '../store/redusers/auth';
import { useToken } from './useToken';
import { LoginRequestData, SignupData } from '../models/auth';

export const useAuth = () => {
	const [loginRequest, { isLoading: isLoginLoading, isError: isLoginError }] = useLogInMutation();
	const [logoutRequest, { isLoading: isLogoutLoading, isError: isLogoutError }] = useLogOutMutation();
	const [signupRequest, { isLoading: isSignupLoading, isError: isSignupError }] = useSignUpMutation();

	const dispatch = useAppDispatch();
	const { setToken } = useToken();

	const logoutHandler = useCallback(async () => {
		await logoutRequest(null);
		dispatch(logout());
	}, []);

	const loginHandler = useCallback(async ({ email, password }: LoginRequestData) => {
		const responce = await loginRequest({ email, password });
		// TODO заменить на данные из ответа
		setToken('access', 'refresh');
	}, []);

	const signupHandler = useCallback(async ({ email, password, name }: SignupData) => {
		await signupRequest({ email, password, name });
		// TODO заменить на данные из ответа
		setToken('access', 'refresh');
	}, []);

	return {
		login: {
			request: loginHandler,
			isLoading: isLoginLoading,
			isError: isLoginError,
		},
		logout: {
			request: logoutHandler,
			isLoading: isLogoutLoading,
			isError: isLogoutError,
		},
		signup: {
			request: signupHandler,
			isLoading: isSignupLoading,
			isError: isSignupError,
		},
	};
};
