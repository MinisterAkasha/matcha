import { Roles } from './roles';
import { Gender } from './gender';

interface UserGeo {
	latitude: string; // ширина
	longitude: string; // долгота
}

interface ImageType {
	url: string;
	type: 'AVATAR' | 'REGULAR';
}

export interface User {
	name: string;
	email: string;
	role: Roles;
	gender: Gender;
	age: number;
	description: string;
	enable: boolean; // забанен или нет
	orientation: any; // TODO добавить enum
	geo: UserGeo;
	tags: string[];
	likes: User[]; // кого пользователь заблокировал
	block: User[]; // кого пользователь заблокировал
	history: User[];
	id: string;
	images: ImageType[];
}
