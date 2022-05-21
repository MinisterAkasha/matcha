import { useCallback } from 'react';
import { authAPI } from '../services/AuthService';
import { useAppDispatch } from './storeHooks';
import { logout } from '../store/redusers/auth';
import { useToken } from './useToken';
import { LoginRequestData, SignupData } from '../models/auth';

export const useAuth = () => {
	const [loginRequest, { isLoading: isLoginLoading, isError: isLoginError }] = authAPI.useLogInMutation();
	const [logoutRequest, { isLoading: isLogoutLoading, isError: isLogoutError }] = authAPI.useLogOutMutation();
	const [signupRequest, { isLoading: isSignupLoading, isError: isSignupError }] = authAPI.useSignUpMutation();

	const dispatch = useAppDispatch();
	const { setToken } = useToken();

	const logoutHandler = useCallback(async () => {
		await logoutRequest(null);
		dispatch(logout());
	}, []);

	const loginHandler = useCallback(async ({ email, password }: LoginRequestData) => {
		const response = await loginRequest({ email, password });

		if ('data' in response) {
			const { accessToken, refreshToken } = response.data;
			setToken(accessToken, refreshToken);
		} else if ('error' in response) {
			console.error('login error', response.error);
		}
	}, []);

	const signupHandler = useCallback(async ({ email, password, username }: SignupData) => {
		const response = await signupRequest({ email, password, username });

		if ('data' in response) {
			const { accessToken, refreshToken } = response.data;
			setToken(accessToken, refreshToken);
		} else if ('error' in response) {
			console.error('signup error', response.error);
		}
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
