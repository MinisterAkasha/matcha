import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { User } from '../../../models/user';

interface AuthState {
	token: string | null;
}

const initialState: AuthState = {
	token: localStorage.getItem('access_token') || null,
};

export const AuthSlice = createSlice({
	name: 'auth',
	initialState,
	reducers: {
		setCredentials: (
			state,
			{ payload: { token, refreshToken } }: PayloadAction<{ token: string; refreshToken: string }>,
		) => {
			state.token = token;
			localStorage.setItem('access_token', token);
			localStorage.setItem('refresh_token', refreshToken);
		},
		logout: () => {
			localStorage.removeItem('access_token');
			localStorage.removeItem('refresh_token');
		},
	},
});

export const { setCredentials, logout } = AuthSlice.actions;
