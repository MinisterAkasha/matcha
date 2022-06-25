import React, { useEffect, useRef, useState } from 'react';
import styled, { css } from 'styled-components';
import { useTinderCard } from '../../hooks/useTinderCard';

interface CandidateCardProps {
	url: string;
	order: number;
}

const StyledCard = styled.div<{ url: string; order: number }>`
	position: absolute;
	display: flex;
	align-items: center;
	flex-direction: column;
	width: 60%;
	height: 600px;
	overflow: hidden;
	border-radius: 30px;
	transition: transform 0.1s ease;
	cursor: pointer;
	${({ url, order }) => css`
		background: red url(${url}) center / cover no-repeat;
		z-index: ${order};
	`}
`;

const CandidatePhoto = styled.img`
	object-fit: cover;
	width: 100%;
	height: 100%;
	box-shadow: 10px 10px inset red;
`;

const ActionButtons = styled.div`
	position: absolute;
	bottom: 10%;
	z-index: 2;
`;

const Shadow = styled.div`
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 30%;
	background-image: linear-gradient(
		0deg,
		hsla(0, 0%, 35.29%, 0) 0%,
		hsla(0, 0%, 34.53%, 0.034375) 16.36%,
		hsla(0, 0%, 32.42%, 0.125) 33.34%,
		hsla(0, 0%, 29.18%, 0.253125) 50.1%,
		hsla(0, 0%, 24.96%, 0.4) 65.75%,
		hsla(0, 0%, 19.85%, 0.546875) 79.33%,
		hsla(0, 0%, 13.95%, 0.575) 86.08%,
		hsla(0, 0%, 7.32%, 0.165625) 97.43%,
		hsla(0, 0%, 0%, 0) 100%
	);
`;

const CandidateCard = ({ url, order }: CandidateCardProps) => {
	const cardRef = useRef<HTMLDivElement>(null);
	const [, setState] = useState(false);

	useTinderCard({
		ref: cardRef,
		onSwipe: () => null,
		onSwipeRequirementFulfilled: () => console.log('onSwipeRequirementFulfilled'),
		preventSwipe: ['up', 'down'],
	});

	useEffect(() => {
		setState(true);
	}, []);

	return (
		<StyledCard url={url} order={order} ref={cardRef}>
			<ActionButtons>button</ActionButtons>
			<Shadow />
		</StyledCard>
	);
};

export default CandidateCard;
