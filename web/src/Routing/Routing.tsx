import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { RoutesPath } from './Routes';
import Main from '../Pages/Main/Main';
import ProtectedRouter from '../Components/ProtectedRouter/ProtectedRouter';
import Auth from '../Pages/Auth/Auth';

const Routing = () => {
	return (
		<Routes>
			<Route path={RoutesPath.AUTH} element={<Auth />} />
			<Route path={RoutesPath.RESET_PASSWORD} element={<Auth />} />
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
