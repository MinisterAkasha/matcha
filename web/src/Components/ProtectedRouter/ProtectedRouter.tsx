import React, { FC } from 'react';
import { Navigate, useLocation } from 'react-router-dom';

const ProtectedRouter: FC<any> = ({ children }) => {
	const location = useLocation();

	// TODO заменить на проверку авторизации
	if (true) {
		return <Navigate to="/login" state={{ from: location }} replace />;
	}

	return children;
};

export default ProtectedRouter;
