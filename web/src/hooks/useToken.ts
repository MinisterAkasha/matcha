import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './storeHooks';
import { setCredentials } from '../store/redusers/auth';

export const useToken = () => {
	const getToken = () => {
		return localStorage.getItem('access_token');
	};

	const { token } = useAppSelector((store) => store.token);
	const dispatch = useAppDispatch();

	useEffect(() => {
		const userToken = getToken();

		if (token) {
			dispatch(setCredentials({ token: userToken || '' }));
		}
	});

	const saveToken = (userToken: string) => {
		dispatch(setCredentials({ token: userToken }));
	};

	return {
		setToken: saveToken,
		token,
	};
};
