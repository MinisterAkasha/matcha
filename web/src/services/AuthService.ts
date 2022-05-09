import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/dist/query/react';
import { EndPoints } from '../api/endpoints';
import { User } from '../models/user';

export const authAPI = createApi({
	reducerPath: 'authAPI',
	tagTypes: ['Auth'],
	baseQuery: fetchBaseQuery({
		baseUrl: 'https://jsonplaceholder.typicode.com',
		prepareHeaders: (headers) => {
			const token = localStorage.getItem('access_token');

			if (token) {
				headers.set('authorization', `Bearer ${token}`);
			}

			return headers;
		},
	}),
	endpoints: (build) => ({
		getUser: build.query<User, string>({
			query: (id) => ({
				// url: EndPoints.GET_USER,
				url: `/users/${id}`,
				params: {
					id,
				},
			}),
			providesTags: (result) => ['Auth'],
		}),
		logIn: build.mutation<User, string>({
			query: () => ({
				url: EndPoints.LOG_IN,
				method: 'POST',
			}),
			invalidatesTags: ['Auth'],
		}),
		logOut: build.mutation<null, null>({
			query: () => ({
				url: EndPoints.LOG_OUT,
				method: 'POST',
			}),
			invalidatesTags: ['Auth'],
		}),
		signUp: build.mutation<User, string>({
			query: () => ({
				url: EndPoints.SIGN_UP,
				method: 'POST',
			}),
			invalidatesTags: ['Auth'],
		}),
	}),
});
