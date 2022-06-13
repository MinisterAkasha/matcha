import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

import { RoutesPath } from '../../Routing/Routes';

const LinkList = styled.ul`
	display: flex;
	align-items: center;
	padding-left: 0;
	list-style: none;
	margin: 0;
`;

const LinkItem = styled.li`
	margin-right: 10px;
`;

const HeaderMenu = () => {
	return (
		<nav>
			<LinkList>
				<Link to={RoutesPath.FEED}>
					<LinkItem>Главная</LinkItem>
				</Link>
				<Link to={RoutesPath.PROFILE}>
					<LinkItem>Профиль</LinkItem>
				</Link>
			</LinkList>
		</nav>
	);
};

export default HeaderMenu;
