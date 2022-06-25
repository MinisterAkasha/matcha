import React from 'react';
import { useGetCurrentUserDataQuery } from '../../services/CurrentUserService';
import { Avatar } from '../../Components/Avatar/Avatar';

const Profile = () => {
	const { data, isLoading } = useGetCurrentUserDataQuery(null);

	const avatar = data ? data.images.filter((image) => image.type === 'AVATAR')[0].url : undefined;

	return (
		<>
			<h1>Profile</h1>
			<Avatar url={avatar} isLoading={isLoading} size={200} />
		</>
	);
};

export default Profile;
