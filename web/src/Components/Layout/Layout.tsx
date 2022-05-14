import React, { FC } from 'react';
import styled from 'styled-components';

const StyledContainer = styled.div`
	max-width: 1440px;
	margin: 0 auto;
	padding: 40px 150px;
`;

const Layout: FC<any> = ({ children }) => {
	return <StyledContainer>{children}</StyledContainer>;
};

export default Layout;
