import { combineReducers, configureStore } from '@reduxjs/toolkit';
import { authAPI } from '../services/AuthService';
import { AuthSlice, logout } from './redusers/auth';

const appReducer = combineReducers({
	token: AuthSlice.reducer,
	[authAPI.reducerPath]: authAPI.reducer,
});

// @ts-ignore
const rootReducer = (state, action) => {
	if (logout.match(action)) {
		state = undefined;
	}

	return appReducer(state, action);
};

export const setupStore = () => {
	return configureStore({
		reducer: rootReducer,
		middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(authAPI.middleware),
	});
};

export type RootState = ReturnType<typeof rootReducer>;
export type AppStore = ReturnType<typeof setupStore>;
export type AppDispatch = AppStore['dispatch'];
