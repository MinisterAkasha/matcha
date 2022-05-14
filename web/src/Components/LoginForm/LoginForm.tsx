import React, { useCallback } from 'react';
import styled from 'styled-components';
import { Form } from 'antd';
import { CommonContainer, StyledForm, StyledTitle, validateMessages } from '../SignUpForm/SignUpForm';
import Button from '../Button/Button';
import Link from '../Link/Link';
import { RoutesPath } from '../../Routing/Routes';
import { Input, PasswordInput } from '../Input/Input';
import { useFormDisabled } from '../../hooks/useFormDisabled';
import Loader from '../Loader/Loader';
import { useLogInMutation } from '../../services/AuthService';
import { useToken } from '../../hooks/useToken';

const Container = styled(CommonContainer)`
	left: 0;
	width: 50%;
	z-index: 2;
`;

const LoginForm = () => {
	const [form] = Form.useForm();
	const [login, { isLoading, isError }] = useLogInMutation();
	const { disabled, handleFormChange } = useFormDisabled(form);
	const { setToken } = useToken();

	const onFinish = useCallback(async () => {
		const { email, password } = form.getFieldsValue();
		await login({ email, password });
		setToken('123');
	}, []);

	return (
		<Container className="container--signin">
			<StyledForm
				onFinish={onFinish}
				form={form}
				onFieldsChange={handleFormChange}
				validateMessages={validateMessages}
				name="login-form"
			>
				<StyledTitle>Войти</StyledTitle>
				<Form.Item rules={[{ required: true, type: 'email' }]} name="email">
					<Input placeholder="Email" />
				</Form.Item>
				<Form.Item rules={[{ required: true, message: 'Введите пароль' }]} name="password">
					<PasswordInput placeholder="Пароль" />
				</Form.Item>
				<Link to={RoutesPath.RESET_PASSWORD}>Забыли пароль?</Link>
				<Button disabled={disabled}>{isLoading ? <Loader /> : 'Отправить'}</Button>
			</StyledForm>
		</Container>
	);
};

export default LoginForm;
