import React from 'react';
import styled from 'styled-components';
import CandidateCard from '../../Components/CandidateCard/CandidateCard';

const CANDIDATE_CARD_WIDTH = '60%';
const CANDIDATE_CARD_HEIGHT = '600px';

const FeedContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	position: relative;
	min-width: ${CANDIDATE_CARD_WIDTH};
	min-height: ${CANDIDATE_CARD_HEIGHT};
`;

const users = [
	{
		url: 'https://i.pinimg.com/550x/31/23/2f/31232fe4b51b47763282524f008d9081.jpg',
		id: '1',
	},
	{
		url: 'https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cGhvdG98ZW58MHx8MHx8&w=1000&q=80',
		id: '2',
	},
];

const Feed = () => {
	return (
		<FeedContainer>
			<h1>Feed</h1>
			{users.map((user, i) => (
				<CandidateCard url={user.url} key={user.id} order={users.length - i} />
			))}
		</FeedContainer>
	);
};

export default Feed;
