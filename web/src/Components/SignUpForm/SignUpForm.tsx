import React, { useCallback, useState } from 'react';
import styled from 'styled-components';
import { Form } from 'antd';
import { FormProps } from 'antd/lib/form/Form';

import { useSignUpMutation } from '../../services/AuthService';
import Loader from '../Loader/Loader';
import Button from '../Button/Button';
import { Input, PasswordInput } from '../Input/Input';
import { useFormDisabled } from '../../hooks/useFormDisabled';

export const CommonContainer = styled.div`
	height: 100%;
	position: absolute;
	top: 0;
	transition: all 0.6s ease-in-out;
`;

const Container = styled(CommonContainer)`
	left: 0;
	opacity: 0;
	width: 50%;
	z-index: 1;
`;

export const StyledForm = styled(Form)`
	background-color: var(--white);
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 3rem;
	height: 100%;
	text-align: center;
`;

export const StyledTitle = styled.h2`
	font-weight: 300;
	margin: 0 0 1.25rem;
`;

const layout: FormProps = {
	wrapperCol: {
		span: 24,
	},
	size: 'middle',
	layout: 'horizontal',
};

/* eslint-disable no-template-curly-in-string */
export const validateMessages = {
	required: 'Введите ${label}',
	types: {
		email: 'Невалидный формат ${label}',
	},
};
/* eslint-enable no-template-curly-in-string */

const SignUpForm = () => {
	const [form] = Form.useForm();
	const [signUp, { isLoading, isError }] = useSignUpMutation();
	const { disabled, handleFormChange } = useFormDisabled(form);

	const onFinish = useCallback(async () => {
		const { email, password } = form.getFieldsValue();
		await signUp({ email, password });
	}, []);

	return (
		<Container className="container--signup">
			<StyledForm
				{...layout}
				onFieldsChange={handleFormChange}
				onFinish={onFinish}
				form={form}
				name="signup-form"
				validateMessages={validateMessages}
			>
				<StyledTitle>Регистрация</StyledTitle>
				<Form.Item rules={[{ required: true, message: 'Введите полное имя' }]} name="name">
					<Input placeholder="Имя" />
				</Form.Item>
				<Form.Item rules={[{ required: true, type: 'email' }]} name="email">
					<Input placeholder="Email" />
				</Form.Item>
				<Form.Item rules={[{ required: true, message: 'Введите пароль' }]} name="password">
					<PasswordInput placeholder="Пароль" />
				</Form.Item>
				<Form.Item>
					<Button type="primary" htmlType="submit" disabled={disabled} className="btn">
						{isLoading ? <Loader /> : 'Отправить'}
					</Button>
				</Form.Item>
			</StyledForm>
		</Container>
	);
};

export default SignUpForm;
