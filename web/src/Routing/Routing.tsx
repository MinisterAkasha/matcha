import React from 'react';
import { Outlet, Route, Routes } from 'react-router-dom';
import { RoutesPath } from './Routes';

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
			<Route path={RoutesPath.HOME} element={<A />} />
		</Routes>
	);
};

export default Routing;
