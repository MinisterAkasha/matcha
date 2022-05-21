import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { RoutesPath } from './Routes';
import Main from '../Pages/Main/Main';
import Auth from '../Pages/Auth/Auth';
import { useAccessibleRoute } from '../hooks/useAccessibleRoute';
import ProtectedRoute from '../Components/ProtectedRoute/ProtectedRoute';
import { useToken } from '../hooks/useToken';

const Routing = () => {
	const { token } = useToken();

	return (
		<Routes>
			<Route path={RoutesPath.AUTH} element={<Auth />} />
			<Route path={RoutesPath.RESET_PASSWORD} element={<Auth />} />
			<Route
				path={RoutesPath.MAIN}
				element={
					<ProtectedRoute isAllowed={!!token}>
						<Main />
					</ProtectedRoute>
				}
			/>
		</Routes>
	);
};

export default Routing;
