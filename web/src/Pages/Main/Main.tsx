import React from 'react';
import { Outlet } from 'react-router-dom';
import Layout from '../../Components/Layout/Layout';
import { useGetCurrentUserDataQuery } from '../../services/CurrentUserService';

const Main = () => {
	// const { data } = useGetCurrentUserDataQuery(null);

	return (
		<Layout>
			<Outlet />
		</Layout>
	);
};

export default Main;
