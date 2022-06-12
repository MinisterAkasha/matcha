export enum RoutesPath {
	FEED = '/feed',
	PROFILE = '/me',
	AUTH = '/auth',
	RESET_PASSWORD = 'reset_password',
	USER = '/user', // используется вместе с id пользователя как query параметр
	CHAT = '/chat', // (Возможно) используется вместе с id пользователя как query параметр ???
}
