import { BaseQueryFn, FetchArgs, fetchBaseQuery, FetchBaseQueryError } from '@reduxjs/toolkit/query';
import { setCredentials, logout } from '../store/redusers/auth';
import { BASE_URL } from './endpoints';

const baseQuery = fetchBaseQuery({
	baseUrl: BASE_URL,
	prepareHeaders: (headers) => {
		const token = localStorage.getItem('access_token');

		if (token) {
			headers.set('authorization', `Bearer ${token}`);
		}

		return headers;
	},
});

const refreshTokenBaseQuery = fetchBaseQuery({
	baseUrl: BASE_URL,
	method: 'POST',
	// body: {
	// 	refreshToken: localStorage.getItem('refresh_token') || '',
	// },
});

export const baseQueryWithReauth: BaseQueryFn<string | FetchArgs, unknown, FetchBaseQueryError> = async (
	args,
	api,
	extraOptions,
) => {
	let result = await baseQuery(args, api, extraOptions);

	if (result.error && result.error.status === 401) {
		const refreshResult = await refreshTokenBaseQuery('/refreshToken', api, extraOptions);

		if (refreshResult.data) {
			// @ts-ignore
			api.dispatch(setCredentials(refreshResult.data));
			result = await baseQuery(args, api, extraOptions);
		} else {
			api.dispatch(logout());
		}
	}

	return result;
};
