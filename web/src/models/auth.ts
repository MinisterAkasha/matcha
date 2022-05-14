export interface LoginRequestData {
	email: string;
	password: string;
}

export interface SignupData extends LoginRequestData {
	name: string;
}

export interface LoginResponseData {
	access_token: string;
	refresh_token: string;
}
