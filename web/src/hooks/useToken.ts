import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './storeHooks';
import { setCredentials } from '../store/redusers/auth';

export const useToken = () => {
	const getToken = () => {
		return {
			accessToken: localStorage.getItem('access_token'),
			refreshToken: localStorage.getItem('refresh_token'),
		};
	};

	const { token } = useAppSelector((store) => store.token);
	const dispatch = useAppDispatch();

	useEffect(() => {
		const { accessToken, refreshToken } = getToken();

		if (accessToken && !token) {
			dispatch(setCredentials({ token: accessToken || '', refreshToken: refreshToken || '' }));
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
