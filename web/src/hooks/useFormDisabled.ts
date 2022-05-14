import { useState } from 'react';
import { FormInstance } from 'antd';

export const useFormDisabled = (form: FormInstance<any>, defaultState = true) => {
	const [disabled, setDisabled] = useState(defaultState);

	const handleFormChange = () => {
		const hasErrors = form.getFieldsError().some(({ errors }) => errors.length);
		const isSomeFieldEmpty = Object.values(form.getFieldsValue()).some((value) => !value);

		setDisabled(hasErrors || isSomeFieldEmpty);
	};

	return { handleFormChange, disabled };
};
