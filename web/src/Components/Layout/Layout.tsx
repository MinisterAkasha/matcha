import React, { FC } from 'react';
import { Layout as CommonLayout } from 'antd';
import Header from '../Header/Header';

const Layout: FC<any> = ({ children }) => {
	return (
		<CommonLayout>
			<Header />
			<CommonLayout.Content>{children}</CommonLayout.Content>
		</CommonLayout>
	);
};

export default Layout;
