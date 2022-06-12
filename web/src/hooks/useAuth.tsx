import { useCallback } from 'react';
import { FetchBaseQueryError } from '@reduxjs/toolkit/query';
import { SerializedError } from '@reduxjs/toolkit';
import { authAPI } from '../services/AuthService';
import { useAppDispatch } from './storeHooks';
import { logout } from '../store/redusers/auth';
import { useToken } from './useToken';
import { LoginRequestData, LoginStatus, SignupData, TokenData } from '../models/auth';

interface LoginResponseType {
	data: LoginStatus & TokenData;
}

export const useAuth = () => {
	const [loginRequest, { isLoading: isLoginLoading, isError: isLoginError }] = authAPI.useLogInMutation();
	const [logoutRequest, { isLoading: isLogoutLoading, isError: isLogoutError }] = authAPI.useLogOutMutation();
	const [signupRequest, { isLoading: isSignupLoading, isError: isSignupError }] = authAPI.useSignUpMutation();

	const dispatch = useAppDispatch();
	const { setToken } = useToken();

	const responseHandler = <T extends { data: LoginStatus & any } | { error: FetchBaseQueryError | SerializedError }>(
		response: T,
	) => {
		const hasData = 'data' in response;
		const hasAuthError = hasData && !response.data.status;
		const hasRequestError = 'error' in response;

		if (hasData && !hasAuthError) {
			const { accessToken, refreshToken } = response.data;
			setToken(accessToken, refreshToken);
		} else if (hasData && hasAuthError) {
			console.error('Ошибка авторизации ', response.data?.message);
		} else if ('error' in response) {
			console.error('login error', response.error);
		}
	};

	const logoutHandler = useCallback(async () => {
		await logoutRequest(null);
		dispatch(logout());
	}, []);

	const loginHandler = useCallback(async ({ email, password }: LoginRequestData) => {
		const response = await loginRequest({ email, password });
		responseHandler(response);

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
