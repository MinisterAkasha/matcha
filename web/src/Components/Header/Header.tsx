import React from 'react';
import styled from 'styled-components';
import { Button, Layout as CommonLayout } from 'antd';
import { useAuth } from '../../hooks/useAuth';
import HeaderMenu from './HeaderMenu';

const HeaderLayout = styled(CommonLayout.Header)`
	display: flex;
	align-items: center;
`;

const LogoutButton = styled(Button)`
	margin-left: auto;
`;

const Header = () => {
	const { logout } = useAuth();

	return (
		<HeaderLayout>
			<HeaderMenu />
			<LogoutButton onClick={logout.request} type="primary">
				log out
			</LogoutButton>
		</HeaderLayout>
	);
};

export default Header;
