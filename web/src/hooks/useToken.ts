import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './storeHooks';
import { setCredentials, logout } from '../store/redusers/auth';

export const useToken = () => {
	const getToken = () => {
		return {
			accessToken: localStorage.getItem('access_token'),
			refreshToken: localStorage.getItem('refresh_token'),
		};
	};

	const { token, isAuth } = useAppSelector((store) => store.auth);
	const dispatch = useAppDispatch();

	useEffect(() => {
		const { accessToken, refreshToken } = getToken();

		if (accessToken && !token && !isAuth) {
			dispatch(setCredentials({ token: accessToken || '', refreshToken: refreshToken || '' }));
		} else if (!accessToken && !token && isAuth) {
			dispatch(logout());
		}
	});

	const saveToken = (userToken: string, refreshToken: string) => {
		dispatch(setCredentials({ token: userToken, refreshToken }));
	};

	return {
		setToken: saveToken,
		token,
	};
};
