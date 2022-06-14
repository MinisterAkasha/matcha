import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { User } from '../../../models/user';

interface AuthState {
	token: string | null;
	isAuth: boolean;
}

const initialState: AuthState = {
	// token: localStorage.getItem('access_token') || null,
	// isAuth: !!localStorage.getItem('access_token') || false,
	token: localStorage.getItem('access_token') || '123',
	isAuth: !!localStorage.getItem('access_token') || true,
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
			state.isAuth = true;
			localStorage.setItem('access_token', token);
			localStorage.setItem('refresh_token', refreshToken);
		},
		logout: (state) => {
			state.token = null;
			state.isAuth = false;
			localStorage.removeItem('access_token');
			localStorage.removeItem('refresh_token');
		},
	},
});

export const { setCredentials, logout } = AuthSlice.actions;
