import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/dist/query/react';
import { BASE_URL, EndPoints } from '../http/endpoints';
import { LoginRequestData, TokenData, SignupData } from '../models/auth';
import { baseQueryWithReauth } from '../http/interceptor';

export const authAPI = createApi({
	reducerPath: 'authAPI',
	tagTypes: ['auth', 'currentUser'],
	baseQuery: baseQueryWithReauth,
	endpoints: (build) => ({
		logIn: build.mutation<TokenData, LoginRequestData>({
			query: (data) => ({
				url: EndPoints.LOG_IN,
				method: 'POST',
				body: data,
			}),
			invalidatesTags: ['auth'],
		}),
		logOut: build.mutation<null, null>({
			query: () => ({
				url: EndPoints.LOG_OUT,
				method: 'POST',
			}),
			invalidatesTags: ['auth'],
		}),
		signUp: build.mutation<TokenData, SignupData>({
			query: (user) => ({
				url: EndPoints.SIGN_UP,
				method: 'POST',
				body: user,
			}),
			invalidatesTags: ['auth'],
		}),
	}),
});

export const { useLogInMutation, useLogOutMutation, useSignUpMutation } = authAPI;
