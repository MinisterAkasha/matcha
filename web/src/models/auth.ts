export interface LoginData {
	email: string;
	password: string;
}

export interface SignupData extends LoginData {
	name: string;
}
