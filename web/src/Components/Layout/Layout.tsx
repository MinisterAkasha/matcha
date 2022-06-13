import React, { FC } from 'react';
import { Layout as CommonLayout } from 'antd';
import Header from '../Header/Header';
import Sidebar from '../Sidebar/Sidebar';

const { Content } = CommonLayout;

const Layout: FC<any> = ({ children }) => {
	return (
		<CommonLayout>
			<Header />
			<CommonLayout>
				<Sidebar />
				<Content>{children}</Content>
			</CommonLayout>
		</CommonLayout>
	);
};

export default Layout;
