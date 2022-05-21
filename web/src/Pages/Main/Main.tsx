import React from 'react';
import Layout from '../../Components/Layout/Layout';
import { useGetCurrentUserDataQuery } from '../../services/CurrentUserService';

const Main = () => {
	const { data } = useGetCurrentUserDataQuery(null);

	return (
		<Layout>
			<h1>Main</h1>
		</Layout>
	);
};

export default Main;
