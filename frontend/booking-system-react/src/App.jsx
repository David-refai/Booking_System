import { useEffect, useState } from 'react';

import './App.css';
import { GetUser } from './components/user/Client.js';
import { Button, Wrap, WrapItem } from '@chakra-ui/react';
import SidebarWithHeader from './components/shared/Sidebar.jsx';
import Card from './components/Card';

function App() {
  const [user, setUser] = useState([]);
  useEffect(() => {
    GetUser().then((user) => {
      setUser(user.data);
    });
  }, []);
  console.log(user);
  return (
    <div>
      <SidebarWithHeader>
        <Wrap  spacing="34px" justify="center">
        {user.map((user) => (
        <WrapItem key={user.id} >
          <Card user={user}/>
        </WrapItem>
        ))}
        </Wrap>
      </SidebarWithHeader>
    </div>
  );
}

export default App;
