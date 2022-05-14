import React from 'react';
import styled, { css } from 'styled-components';
import { Input as CommonInput, InputProps } from 'antd';
import { PasswordProps } from 'antd/es/input';

const inputStyles = css`
	background-color: #fff;
	border: none;
	padding: 0.5rem;
	width: 200px;
`;

const StyledInput = styled(CommonInput)`
	${inputStyles}
`;

const StyledPasswordInput = styled(CommonInput.Password)`
	${inputStyles}
`;

export const Input = (props: InputProps) => <StyledInput {...props} />;
export const PasswordInput = (props: PasswordProps) => <StyledPasswordInput {...props} />;
