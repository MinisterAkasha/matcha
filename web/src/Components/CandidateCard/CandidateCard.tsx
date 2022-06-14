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
	/*transition: transform 0.2s ease;*/
	/*transform-origin: bottom center;*/
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
	const { current: coords } = useRef<{ x: number | null; y: number | null }>({ x: null, y: null });
	const animation = useRef(null);

	// Use useRef for mutable variables that we want to persist
	// without triggering a re-render on their change
	const requestRef = useRef<number | undefined>();
	const previousTimeRef = useRef<number | undefined>();

	useTinderCard({
		ref: cardRef,
		onSwipe: () => console.log('swipe!!!!'),
		// onSwipeRequirementFulfilled: () => console.log('onSwipeRequirementFulfilled'),
	});

	// const animate = (time: number) => {
	// 	if (previousTimeRef.current) {
	// 		const deltaTime = time - previousTimeRef.current;
	//
	// 		// Pass on a function to the setter of the state
	// 		// to make sure we always have the latest state
	// 		// setCount(prevCount => (prevCount + deltaTime * 0.01) % 100);
	// 	}
	// 	previousTimeRef.current = time;
	// 	requestRef.current = requestAnimationFrame(animate);
	// };
	//
	// useEffect(() => {
	// 	requestRef.current = requestAnimationFrame(animate);
	//
	// 	return () => {
	// 		cancelAnimationFrame(requestRef.current as number);
	// 	};
	// }, []);
	//
	// useEffect(() => {
	// 	if (!cardRef.current || !cardRef.current?.addEventListener) {
	// 		return;
	// 	}
	// 	let delta = 0;
	// 	let side = 1;
	// 	let needForRAF = true;
	//
	// 	const update = () => {
	// 		needForRAF = true;
	//
	// 		if (!cardRef.current) {
	// 			return;
	// 		}
	// 		cardRef.current.style.transform = `rotate(${(delta * side) / 10}deg)`;
	// 	};
	//
	// 	const mouseDownHandler = (event: any) => {
	// 		if (!cardRef.current) {
	// 			return;
	// 		}
	//
	// 		coords.x = event.x;
	// 		coords.y = event.y;
	//
	// 		cardRef.current.style.transform = `translateY(-10px)`;
	// 	};
	//
	// 	const mouseMoveHandler = (event: any) => {
	// 		if (!(coords.y && coords.x) || !cardRef.current) {
	// 			return;
	// 		}
	//
	// 		const { x, y } = event;
	// 		delta = Math.sqrt((x - coords.x) ** 2 + (y - coords.y) ** 2);
	// 		side = x > coords.x ? 1 : -1;
	//
	// 		if (needForRAF) {
	// 			needForRAF = false; // no need to call rAF up until next frame
	// 			requestAnimationFrame(update); // request 60fps animation
	// 		}
	// 	};
	//
	// 	const mouseUpHandler = (event: any) => {
	// 		if (!cardRef.current) {
	// 			return;
	// 		}
	// 		coords.x = null;
	// 		coords.y = null;
	// 		delta = 0;
	//
	// 		cardRef.current.style.transform = 'rotate(0)';
	// 	};
	//
	// 	cardRef.current.addEventListener('mousedown', mouseDownHandler);
	// 	cardRef.current.addEventListener('mousemove', mouseMoveHandler);
	//
	// 	window.addEventListener('mouseup', mouseUpHandler);
	//
	// 	return () => {
	// 		if (!cardRef.current) {
	// 			return;
	// 		}
	// 		cardRef.current.removeEventListener('mousedown', mouseDownHandler);
	// 		cardRef.current.removeEventListener('mousemove', mouseMoveHandler);
	// 		window.removeEventListener('mouseup', mouseUpHandler);
	// 	};
	// }, [cardRef]);

	return (
		<StyledCard url={url} order={order} ref={cardRef}>
			<ActionButtons>button</ActionButtons>
			<Shadow />
		</StyledCard>
	);
};

export default CandidateCard;
