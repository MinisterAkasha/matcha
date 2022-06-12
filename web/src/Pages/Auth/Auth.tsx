import React, { useEffect, useState } from 'react';
import styled, { createGlobalStyle, keyframes } from 'styled-components';
import { Navigate, useNavigate } from 'react-router-dom';
import SignUpForm from '../../Components/SignUpForm/SignUpForm';
import LoginForm from '../../Components/LoginForm/LoginForm';
import Button from '../../Components/Button/Button';
import { RoutesPath } from '../../Routing/Routes';
import { useToken } from '../../hooks/useToken';
import { useAppSelector } from '../../hooks/storeHooks';

type SideType = 'left' | 'right';

const ShowAnimation = keyframes`
  0%,
  49.9% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
`;

const AuthStyles = createGlobalStyle`
  .link {
    color: var(--gray);
    font-size: 0.9rem;
    margin: 1.5rem 0;
    text-decoration: none;
  }

  .right-panel-active .container--signin {
    transform: translateX(100%);
  }

  .right-panel-active .container--signup {
    animation: ${ShowAnimation} 0.6s;
    opacity: 1;
    transform: translateX(100%);
    z-index: 5;
  }

  .right-panel-active .container__overlay {
    transform: translateX(-100%);
  }

  .right-panel-active .overlay {
    transform: translateX(50%);
  }

  .overlay--left {
    transform: translateX(-20%);
  }

  .right-panel-active .overlay--left {
    transform: translateX(0);
  }

  .overlay--right {
    right: 0;
    transform: translateX(0);
  }

  .right-panel-active .overlay--right {
    transform: translateX(20%);
  }
`;

const PageContainer = styled.div`
	height: 100vh;
	width: 100vw;
	display: flex;
	align-items: center;
	justify-content: center;
`;

const Container = styled.div`
	background-color: var(--white);
	border-radius: var(--button-radius);
	box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25), 0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
	height: var(--max-height);
	max-width: var(--max-width);
	overflow: hidden;
	position: relative;
	width: 100%;
`;

const OverlayContainer = styled.div`
	height: 100%;
	left: 50%;
	overflow: hidden;
	position: absolute;
	top: 0;
	transition: transform 0.6s ease-in-out;
	width: 50%;
	z-index: 100;
`;

const Overlay = styled.div`
	background: var(--lightblue)
		url('https://res.cloudinary.com/dci1eujqw/image/upload/v1616769558/Codepen/waldemar-brandt-aThdSdgx0YM-unsplash_cnq4sb.jpg')
		no-repeat fixed center;
	background-size: cover;
	height: 100%;
	left: -100%;
	position: relative;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 200%;
`;

const OverlayPanel = styled.div<{ overlaySide: SideType }>`
	align-items: center;
	display: flex;
	flex-direction: column;
	height: 100%;
	justify-content: center;
	position: absolute;
	text-align: center;
	top: 0;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 50%;
`;

const Auth = () => {
	const [activeSide, setActiveSide] = useState<SideType>('left');
	const { isAuth } = useAppSelector((state) => state.auth);

	if (isAuth) {
		return <Navigate to={RoutesPath.FEED} replace={false} />;
	}

	return (
		<PageContainer>
			<Container className={`${activeSide}-panel-active`}>
				<AuthStyles />
				<SignUpForm />
				<LoginForm />

				<OverlayContainer className="container__overlay">
					<Overlay className="overlay">
						<OverlayPanel overlaySide="left" className="overlay--left">
							<Button onClick={() => setActiveSide('left')}>Войти</Button>
						</OverlayPanel>
						<OverlayPanel overlaySide="right" className="overlay--right">
							<Button onClick={() => setActiveSide('right')}>Зарегистрироваться</Button>
						</OverlayPanel>
					</Overlay>
				</OverlayContainer>
			</Container>
		</PageContainer>
	);
};

export default Auth;
