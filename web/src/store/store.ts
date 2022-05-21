import { combineReducers, configureStore } from '@reduxjs/toolkit';
import { authAPI } from '../services/AuthService';
import { AuthSlice, logout } from './redusers/auth';
import { currentUserApi } from '../services/CurrentUserService';

const appReducer = combineReducers({
	auth: AuthSlice.reducer,
	[authAPI.reducerPath]: authAPI.reducer,
	[currentUserApi.reducerPath]: currentUserApi.reducer,
});

// @ts-ignore
const rootReducer = (state, action) => {
	if (logout.match(action)) {
		console.log('logout in reduser');
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
