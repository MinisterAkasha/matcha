import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { User } from '../../../models/user';

interface AuthState {
	token: string | null;
	user: User | null;
}

const initialState: AuthState = {
	token: localStorage.getItem('access_token') || null,
	user: null,
};

export const AuthSlice = createSlice({
	name: 'auth',
	initialState,
	reducers: {
		setCredentials: (state, { payload: { user, token } }: PayloadAction<{ user: User; token: string }>) => {
			state.user = user;
			state.token = token;
			localStorage.setItem('access_token', token);
		},
	},
});
