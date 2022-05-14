import React from 'react';
import { Link as CommonLink, LinkProps } from 'react-router-dom';
import styled from 'styled-components';

const StyledLink = styled(CommonLink)`
	color: var(--gray);
	font-size: 0.9rem;
	margin: 1.5rem 0;
	text-decoration: none;
`;

const Link = (props: LinkProps & React.RefAttributes<HTMLAnchorElement>) => <StyledLink {...props} />;

export default Link;
