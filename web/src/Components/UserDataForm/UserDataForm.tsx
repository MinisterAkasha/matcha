import React from 'react';
import { Button, Col, DatePicker, Form, Input, Row, Select } from 'antd';
import Loader from '../Loader/Loader';

const UserDataForm = () => {
	return (
		<div>
			<Row>
				<Col span={12}>
					<Form.Item label="Имя" rules={[{ required: true }]} name="name">
						<Input />
					</Form.Item>
				</Col>
				<Col span={12}>
					<Form.Item label="Фамилия" rules={[{ required: true }]} name="surname">
						<Input />
					</Form.Item>
				</Col>
			</Row>
			<Form.Item label="Пол" name="sex">
				<Select>
					<Select.Option value="demo">Мужчина</Select.Option>
					<Select.Option value="demo">Женщина</Select.Option>
				</Select>
			</Form.Item>
			<Form.Item label="Дата рождения" name="birthdate">
				<DatePicker placeholder="Выберите дату" />
			</Form.Item>
		</div>
	);
};

export default UserDataForm;
