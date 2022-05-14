import React, { FC } from 'react';
import styled from 'styled-components';
import Header from '../Header/Header';

const StyledContainer = styled.div`
	max-width: 1440px;
	margin: 0 auto;
	padding: 100px 40px 150px 40px;
`;

const Layout: FC<any> = ({ children }) => {
	return (
		<StyledContainer>
			<Header />
			{children}
		</StyledContainer>
	);
};

export default Layout;
