import React, { useCallback } from 'react';

const sleep = (ms: number) => {
	return new Promise((resolve) => {
		setTimeout(resolve, ms);
	});
};

function debounce(this: any, f: any, ms: number) {
	let isCooldown = false;

	return function () {
		if (isCooldown) return;

		// @ts-ignore
		// eslint-disable-next-line prefer-rest-params
		f.apply(this, arguments);

		isCooldown = true;

		setTimeout(() => (isCooldown = false), ms);
	};
}

type direction = 'left' | 'right' | 'up' | 'down' | 'none';

interface useTinderCardProps {
	ref: React.RefObject<HTMLDivElement>;
	flickOnSwipe?: boolean;
	onSwipe?: (dir: direction) => void;
	onCardLeftScreen?: (dir: direction) => void;
	preventSwipe?: Array<string>;
	swipeRequirementType?: 'velocity' | 'position';
	swipeThreshold?: number;
	onSwipeRequirementFulfilled?: (dir: direction) => void;
	onSwipeRequirementUnfulfilled?: () => void;
}

interface Coordinates {
	x: number;
	y: number;
}

interface Location extends Coordinates {
	time: number;
}

const settings = {
	snapBackDuration: 300,
	maxTilt: 5,
	bouncePower: 0.2,
	swipeThreshold: 300, // px/s
};

const translationString = (x: number, y: number) => {
	const translation = `translate(${x}px, ${y}px)`;

	return translation;
};

const rotationString = (rot: number) => {
	const rotation = `rotate(${rot}deg)`;

	return rotation;
};

const getTranslate = (element: Element) => {
	const style = window.getComputedStyle(element);
	const matrix = new WebKitCSSMatrix(style.webkitTransform);
	const ans = { x: matrix.m41, y: -matrix.m42 };

	return ans;
};

const getRotation = (element: Element) => {
	const style = window.getComputedStyle(element);
	const matrix = new WebKitCSSMatrix(style.webkitTransform);
	const ans = (-Math.asin(matrix.m21) / (2 * Math.PI)) * 360;

	return ans;
};

const getElementSize = (element: Element) => {
	const elementStyles = window.getComputedStyle(element);
	const widthString = elementStyles.getPropertyValue('width');
	const width = Number(widthString.split('px')[0]);
	const heightString = elementStyles.getPropertyValue('height');
	const height = Number(heightString.split('px')[0]);

	return { x: width, y: height };
};

const pythagoras = (x: number, y: number) => {
	return Math.sqrt(x ** 2 + y ** 2);
};

const normalize = (vector: Coordinates, multiplier = 1) => {
	const length = Math.sqrt(vector.x ** 2 + vector.y ** 2);

	return { x: (vector.x * multiplier) / length, y: (vector.y * multiplier) / length };
};

const animateOut = async (element: HTMLElement, speed: Coordinates, easeIn = false) => {
	const startPos = getTranslate(element);
	const bodySize = getElementSize(document.body);
	const diagonal = pythagoras(bodySize.x, bodySize.y);

	const velocity = pythagoras(speed.x, speed.y);
	const time = diagonal / velocity;
	const multiplier = diagonal / velocity;

	const translateString = translationString(speed.x * multiplier + startPos.x, -speed.y * multiplier + startPos.y);
	let rotateString = '';

	const rotationPower = 200;

	if (easeIn) {
		element.style.transition = `ease ${time}s`;
	} else {
		element.style.transition = `ease-out ${time}s`;
	}

	if (getRotation(element) === 0) {
		rotateString = rotationString((Math.random() - 0.5) * rotationPower);
	} else if (getRotation(element) > 0) {
		rotateString = rotationString((Math.random() * rotationPower) / 2 + getRotation(element));
	} else {
		rotateString = rotationString(((Math.random() - 1) * rotationPower) / 2 + getRotation(element));
	}

	element.style.transform = translateString + rotateString;

	await sleep(time * 1000);
};

const animateBack = async (element: HTMLElement) => {
	element.style.transition = `${settings.snapBackDuration}ms`;
	const startingPoint = getTranslate(element);
	const translation = translationString(
		startingPoint.x * -settings.bouncePower,
		startingPoint.y * -settings.bouncePower,
	);
	const rotation = rotationString(getRotation(element) * -settings.bouncePower);
	element.style.transform = translation + rotation;

	await sleep(settings.snapBackDuration * 0.75);
	element.style.transform = 'none';

	await sleep(settings.snapBackDuration);
	element.style.transition = '10ms';
};

const getSwipeDirection = (property: Coordinates) => {
	if (Math.abs(property.x) > Math.abs(property.y)) {
		if (property.x > settings.swipeThreshold) {
			return 'right';
		}

		if (property.x < -settings.swipeThreshold) {
			return 'left';
		}
	} else {
		if (property.y > settings.swipeThreshold) {
			return 'up';
		}

		if (property.y < -settings.swipeThreshold) {
			return 'down';
		}
	}

	return 'none';
};

const calcSpeed = (oldLocation: Location, newLocation: Location) => {
	const dx = newLocation.x - oldLocation.x;
	const dy = oldLocation.y - newLocation.y;
	const dt = (newLocation.time - oldLocation.time) / 1000;

	return { x: dx / dt, y: dy / dt };
};

const dragableTouchmove = (
	coordinates: Coordinates,
	element: HTMLElement,
	offset: Coordinates,
	lastLocation: Location,
) => {
	const pos = { x: coordinates.x + offset.x, y: coordinates.y + offset.y };
	const newLocation = { x: pos.x, y: pos.y, time: new Date().getTime() };
	const translation = translationString(pos.x, pos.y);
	const rotCalc = calcSpeed(lastLocation, newLocation).x / 1000;
	const rotation = rotationString(rotCalc * settings.maxTilt);
	element.style.transform = translation + rotation;

	return newLocation;
};

const touchCoordinatesFromEvent = (e: TouchEvent) => {
	const touchLocation = e.targetTouches[0];

	return { x: touchLocation.clientX, y: touchLocation.clientY };
};

const mouseCoordinatesFromEvent = (e: MouseEvent) => {
	return { x: e.clientX, y: e.clientY };
};

export const useTinderCard = ({
	flickOnSwipe = true,
	onSwipe,
	onCardLeftScreen,
	preventSwipe = [],
	swipeRequirementType = 'velocity',
	swipeThreshold = settings.swipeThreshold,
	onSwipeRequirementFulfilled,
	onSwipeRequirementUnfulfilled,
	ref,
}: useTinderCardProps) => {
	settings.swipeThreshold = swipeThreshold;
	const swipeAlreadyReleased = React.useRef(false);
	const element = ref.current;

	const swipe = useCallback(
		async (dir: direction = 'right') => {
			if (!element) {
				return;
			}

			if (onSwipe) {
				onSwipe(dir);
			}
			const power = 1000;
			const disturbance = (Math.random() - 0.5) * 100;
			switch (dir) {
				case 'right': {
					await animateOut(element, { x: power, y: disturbance }, true);
					break;
				}
				case 'left': {
					await animateOut(element, { x: -power, y: disturbance }, true);
					break;
				}
				case 'up': {
					await animateOut(element, { x: disturbance, y: power }, true);
					break;
				}
				default: {
					await animateOut(element, { x: disturbance, y: -power }, true);
				}
			}

			element.style.display = 'none';

			if (onCardLeftScreen) {
				onCardLeftScreen(dir);
			}
		},
		[element, onCardLeftScreen, onSwipe],
	);
	const restoreCard = useCallback(async () => {
		if (!element) {
			return;
		}

		element.style.display = 'block';
		await animateBack(element);
	}, [element]);

	const handleSwipeReleased = React.useCallback(
		async (element: HTMLElement, speed: Coordinates) => {
			if (swipeAlreadyReleased.current) {
				return;
			}
			swipeAlreadyReleased.current = true;

			const currentPosition = getTranslate(element);
			// Check if this is a swipe
			const dir = getSwipeDirection(swipeRequirementType === 'velocity' ? speed : currentPosition);

			if (dir !== 'none') {
				if (onSwipe) {
					onSwipe(dir);
				}

				if (flickOnSwipe && !preventSwipe.includes(dir)) {
					const outVelocity = swipeRequirementType === 'velocity' ? speed : normalize(currentPosition, 600);
					await animateOut(element, outVelocity);
					element.style.display = 'none';

					if (onCardLeftScreen) {
						onCardLeftScreen(dir);
					}

					return;
				}
			}

			// Card was not flicked away, animate back to start
			animateBack(element);
		},
		[swipeAlreadyReleased, flickOnSwipe, onSwipe, onCardLeftScreen, preventSwipe, swipeRequirementType],
	);

	const handleSwipeStart = React.useCallback(() => {
		swipeAlreadyReleased.current = false;
	}, [swipeAlreadyReleased]);

	React.useLayoutEffect(() => {
		if (!element) {
			return;
		}
		// @ts-ignore
		let offset: Coordinates = { x: null, y: null };
		let speed = { x: 0, y: 0 };
		let lastLocation = { x: 0, y: 0, time: new Date().getTime() };
		let mouseIsClicked = false;
		let swipeThresholdFulfilledDirection = 'none';

		const handleMove = (coordinates: Coordinates) => {
			if (!element) {
				return;
			}

			// Check fulfillment
			const dir = getSwipeDirection(swipeRequirementType === 'velocity' ? speed : getTranslate(element));

			if (dir !== swipeThresholdFulfilledDirection) {
				swipeThresholdFulfilledDirection = dir;

				if (swipeThresholdFulfilledDirection === 'none') {
					if (onSwipeRequirementUnfulfilled) {
						onSwipeRequirementUnfulfilled();
					}
				} else if (onSwipeRequirementFulfilled) {
					onSwipeRequirementFulfilled(dir);
				}
			}

			// Move
			const newLocation = dragableTouchmove(coordinates, element, offset, lastLocation);
			speed = calcSpeed(lastLocation, newLocation);
			lastLocation = newLocation;
		};

		const dragHandler = (event: MouseEvent | TouchEvent) => {
			event.preventDefault();

			mouseIsClicked = true;
			handleSwipeStart();

			if (event instanceof MouseEvent) {
				offset = { x: -mouseCoordinatesFromEvent(event).x, y: -mouseCoordinatesFromEvent(event).y };
			} else if (event instanceof TouchEvent) {
				offset = { x: -touchCoordinatesFromEvent(event).x, y: -touchCoordinatesFromEvent(event).y };
			}
		};

		const dropHandler = (event: MouseEvent | TouchEvent) => {
			event.preventDefault();

			mouseIsClicked = false;
			handleSwipeReleased(element, speed);
		};

		const mouseMoveHandler = (event: MouseEvent) => {
			event.preventDefault();

			if (mouseIsClicked) {
				handleMove(mouseCoordinatesFromEvent(event));
			}
		};

		const touchMoveHandler = (event: TouchEvent) => {
			event.preventDefault();
			handleMove(touchCoordinatesFromEvent(event));
		};

		element.addEventListener('touchstart', dragHandler);
		element.addEventListener('mousedown', dragHandler);

		element.addEventListener('touchmove', touchMoveHandler);
		element.addEventListener('mousemove', mouseMoveHandler);

		element.addEventListener('touchend', dropHandler);
		element.addEventListener('mouseup', dropHandler);
		element.addEventListener('mouseleave', dropHandler);

		return () => {
			element.removeEventListener('touchstart', dragHandler);
			element.removeEventListener('mousedown', dragHandler);

			element.removeEventListener('touchmove', touchMoveHandler);
			element.removeEventListener('mousemove', mouseMoveHandler);

			element.removeEventListener('touchend', dropHandler);
			element.removeEventListener('mouseup', dropHandler);
			element.removeEventListener('mouseleave', dropHandler);
		};
	}, [element]);

	return { swipe, restoreCard };
};
