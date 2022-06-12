export interface LoginRequestData {
	email: string;
	password: string;
}

export interface SignupData extends LoginRequestData {
	username: string;
}

export interface TokenData {
	accessToken: string;
	refreshToken: string;
}

export interface LoginStatus {
	success: boolean;
	message?: string;
}
