import React from 'react';
import { UserOutlined } from '@ant-design/icons';
import styled, { css } from 'styled-components';
import { Skeleton } from 'antd';

interface AvatarProps {
	url: string | undefined;
	isLoading: boolean;
	size: number;
}

const AVATAR_SIZE = 200;

const AvatarContainer = styled.div<Pick<AvatarProps, 'size'>>`
	${({ size }) => css`
		width: ${size}px;
		height: ${size}px;
	`}
	border-radius: 50%;
	overflow: hidden;
`;

const Image = styled.img`
	width: 100%;
	height: 100%;
	object-fit: cover;
`;

const DefaultAvatar = styled(UserOutlined)`
	width: 100%;
	height: 100%;
	font-size: 200px;
`;

export const Avatar = ({ url, isLoading, size }: AvatarProps) => {
	const AvatarComponent = url ? <Image src={url} alt="avatar" /> : <DefaultAvatar />;

	return (
		<AvatarContainer size={size}>
			{isLoading ? <Skeleton.Avatar size={size} active shape="circle" /> : AvatarComponent}
		</AvatarContainer>
	);
};
