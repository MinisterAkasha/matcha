import React from 'react';
import styled from 'styled-components';
import { Button } from 'antd';
import { useAuth } from '../../hooks/useAuth';

const FixedHeader = styled.header`
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	height: 100px;
`;

const HeaderContent = styled.div`
	padding: 15px;
`;

const Header = () => {
	const { logout } = useAuth();

	return (
		<FixedHeader>
			<HeaderContent>
				<Button onClick={logout.request} type="primary">
					log out
				</Button>
			</HeaderContent>
		</FixedHeader>
	);
};

export default Header;
