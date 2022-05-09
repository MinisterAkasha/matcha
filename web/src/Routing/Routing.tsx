import React from 'react';
import { Outlet, Route, Routes } from 'react-router-dom';
import { RoutesPath } from './Routes';
import Login from '../Pages/Login/Login';
import SignUp from '../Pages/SignUp/SignUp';
import Main from '../Pages/Main/Main';
import ProtectedRouter from '../Components/ProtectedRouter/ProtectedRouter';

const A = () => {
	return (
		<>
			<h1>app</h1>
			<Outlet />
		</>
	);
};

const Routing = () => {
	return (
		<Routes>
			<Route path={RoutesPath.LOGIN} element={<Login />} />
			<Route path={RoutesPath.SIGN_UP} element={<SignUp />} />
			<Route
				path={RoutesPath.MAIN}
				element={
					<ProtectedRouter>
						<Main />
					</ProtectedRouter>
				}
			/>
		</Routes>
	);
};

export default Routing;
