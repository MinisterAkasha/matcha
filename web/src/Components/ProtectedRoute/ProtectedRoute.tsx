import React, { FC, PropsWithChildren } from 'react';
import { Navigate } from 'react-router-dom';

interface ProtectedRouteProps extends PropsWithChildren<unknown> {
	redirectPath?: string;
	isAllowed: boolean;
}

const ProtectedRoute: FC<ProtectedRouteProps> = ({ isAllowed, redirectPath = '/auth', children }) => {
	if (!isAllowed) {
		return <Navigate to={redirectPath} replace />;
	}

	// eslint-disable-next-line react/jsx-no-useless-fragment
	return <>{children}</>;
};

export default ProtectedRoute;
