import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { RoutesPath } from './Routes';
import Main from '../Pages/Main/Main';
import Auth from '../Pages/Auth/Auth';
import ProtectedRoute from '../Components/ProtectedRoute/ProtectedRoute';
import { useToken } from '../hooks/useToken';
import { useAppSelector } from '../hooks/storeHooks';

const Routing = () => {
	const { isAuth } = useAppSelector((state) => state.auth);

	console.log('isAuth', isAuth);

	return (
		<Routes>
			<Route path={RoutesPath.AUTH} element={<Auth />} />
			<Route path={RoutesPath.RESET_PASSWORD} element={<Auth />} />
			<Route
				path={RoutesPath.MAIN}
				element={
					<ProtectedRoute isAllowed={isAuth}>
						<Main />
					</ProtectedRoute>
				}
			/>
		</Routes>
	);
};

export default Routing;
