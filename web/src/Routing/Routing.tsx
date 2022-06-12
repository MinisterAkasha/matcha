import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { RoutesPath } from './Routes';
import Main from '../Pages/Main/Main';
import Auth from '../Pages/Auth/Auth';
import ProtectedRoute from '../Components/ProtectedRoute/ProtectedRoute';
import { useAppSelector } from '../hooks/storeHooks';
import Profile from '../Pages/Profile/Profile';
import Feed from '../Pages/Feed/Feed';

const Routing = () => {
	const { isAuth } = useAppSelector((state) => state.auth);

	return (
		<Routes>
			<Route path={RoutesPath.AUTH} element={<Auth />} />
			<Route path={RoutesPath.RESET_PASSWORD} element={<Auth />} />
			<Route
				path="/"
				element={
					<ProtectedRoute isAllowed={isAuth}>
						<Main />
					</ProtectedRoute>
				}
			>
				<Route path={RoutesPath.PROFILE} element={<Profile />} />
				<Route path={RoutesPath.FEED} element={<Feed />} />
			</Route>
		</Routes>
	);
};

export default Routing;
