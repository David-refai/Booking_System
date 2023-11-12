
import './App.css';
import Trip, { loader as menuLoader } from './components/trip/Trip';

import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import AddTripForm from './components/view/trip/AddTripForm';
import AppLayout from './AppLayout';

function App() {

  const router = createBrowserRouter([
    {
      element: <AppLayout />,
      children: [
        {
          path: '/',
          element: <Trip />,
          loader: menuLoader,
        },
      ],
    },
    {
      path: '/add-new-trip',
      element: <AddTripForm />,
    },
  ]);

  return <RouterProvider router={router}/>
}

export default App;
