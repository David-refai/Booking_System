import { useEffect, useState } from 'react';

import './App.css';
import SidebarWithHeader from './components/shared/Sidebar.jsx';
import Trip from './components/trip/Trip';

function App() {

  return (
    <div>
      <SidebarWithHeader>
        <Trip />
      </SidebarWithHeader>
    </div>
  );
}

export default App;
