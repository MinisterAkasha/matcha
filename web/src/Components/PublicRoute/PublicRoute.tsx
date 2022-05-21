import React from 'react';
import { useToken } from '../../hooks/useToken';

const PublicRoute = () => {
	const { token } = useToken();

	// return <Route {...rest} element={!auth.user ? element : <Navigate to="/protected" replace />} />;
	return <h1>123</h1>;
};

export default PublicRoute;
