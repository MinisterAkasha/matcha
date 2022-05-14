import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/dist/query/react';
import { EndPoints } from '../api/endpoints';
import { User } from '../models/user';
import { LoginData } from '../models/auth';

export const authAPI = createApi({
	reducerPath: 'authAPI',
	tagTypes: ['current_user'],
	baseQuery: fetchBaseQuery({
		baseUrl: 'http://localhost:5000/',
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
			providesTags: (result) => ['current_user'],
		}),
		logIn: build.mutation<User, LoginData>({
			query: (data) => ({
				url: EndPoints.LOG_IN,
				method: 'POST',
				body: data,
			}),
			invalidatesTags: ['current_user'],
		}),
		logOut: build.mutation<null, null>({
			query: () => ({
				url: EndPoints.LOG_OUT,
				method: 'POST',
			}),
			invalidatesTags: ['current_user'],
		}),
		signUp: build.mutation<User, any>({
			query: (user) => ({
				url: EndPoints.SIGN_UP,
				method: 'POST',
				body: user,
			}),
			invalidatesTags: ['current_user'],
		}),
	}),
});

export const { useGetUserQuery, useLogInMutation, useLogOutMutation, useSignUpMutation } = authAPI;
