import { EndPoints } from '../http/endpoints';
import { User } from '../models/user';
import { authAPI } from './AuthService';

export const currentUserApi = authAPI.injectEndpoints({
	endpoints: (build) => ({
		getCurrentUserData: build.query<User, null>({
			query: () => ({
				url: EndPoints.GET_CURRENT_USER,
				method: 'GET',
			}),
			providesTags: (res) => ['currentUser'],
		}),
	}),
});

export const { useGetCurrentUserDataQuery } = currentUserApi;
