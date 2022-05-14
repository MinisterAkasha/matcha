import React from 'react';
import Routing from './Routing/Routing';
import 'antd/dist/antd.css';
import { GlobalStyles } from './Components/GlobalStyles/GlobalStyles';

const App = () => {
	return (
		<>
			<GlobalStyles />
			<Routing />
		</>
	);
};

export default App;
