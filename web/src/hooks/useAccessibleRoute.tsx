import { useNavigate } from 'react-router-dom';
import React, { useEffect } from 'react';
import { RoutesPath } from '../Routing/Routes';
import { useToken } from './useToken';
import { useAppSelector } from './storeHooks';

export const useAccessibleRoute = () => {
	const navigate = useNavigate();
	const { token } = useToken();
	console.log('token', token);

	useEffect(() => {
		if (token) {
			return navigate(RoutesPath.MAIN);
		}

		return navigate(RoutesPath.AUTH);
	});
};
