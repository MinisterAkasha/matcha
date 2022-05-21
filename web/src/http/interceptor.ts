import { BaseQueryFn, FetchArgs, fetchBaseQuery, FetchBaseQueryError } from '@reduxjs/toolkit/query';
import { setCredentials, logout } from '../store/redusers/auth';
import { BASE_URL, EndPoints } from './endpoints';

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
});

export const baseQueryWithReauth: BaseQueryFn<string | FetchArgs, unknown, FetchBaseQueryError> = async (
	args,
	api,
	extraOptions,
) => {
	let result = await baseQuery(args, api, extraOptions);

	if (result.error && result.error.status === 401) {
		const refreshResult = await refreshTokenBaseQuery(
			{
				url: EndPoints.REFRESH_TOKEN,
				method: 'POST',
				body: {
					refreshToken: localStorage.getItem('refresh_token') || '',
				},
			},
			api,
			extraOptions,
		);

		if (refreshResult.data) {
			const { accessToken, refreshToken } = refreshResult.data as { accessToken: string; refreshToken: string };
			// @ts-ignore
			api.dispatch(setCredentials({ token: accessToken, refreshToken }));
			result = await baseQuery(args, api, extraOptions);
		} else {
			api.dispatch(logout());
		}
	}

	return result;
};
