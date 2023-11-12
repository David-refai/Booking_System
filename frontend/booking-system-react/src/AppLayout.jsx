// AppLayout.js

import SidebarWithHeader from './components/shared/Sidebar';
import { Outlet } from 'react-router-dom';

export default function AppLayout() {
  return (
    <div>
      <SidebarWithHeader>
        <main>
          <Outlet />
        </main>
      </SidebarWithHeader>
    </div>
  );
}
