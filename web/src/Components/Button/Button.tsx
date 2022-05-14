import React, { FC } from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
	background-color: var(--blue);
	background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
	border-radius: 20px;
	border: 1px solid var(--blue);
	color: var(--white);
	cursor: pointer;
	font-size: 0.8rem;
	font-weight: bold;
	letter-spacing: 0.1rem;
	padding: 0.9rem 4rem;
	text-transform: uppercase;
	transition: transform 80ms ease-in;

	&:active:enabled {
		transform: scale(0.95);
	}

	&:hover:enabled {
		transform: scale(1.05);
	}

	&:focus {
		outline: none;
	}

	&:disabled {
		opacity: 0.4;
	}
`;

const Button: FC<any> = (props) => <StyledButton {...props} />;

export default Button;
