
import { GetTrip } from '../service/TripService';
import TripCard from '../view/trip/TripCard';
import { Wrap, WrapItem } from '@chakra-ui/react';
import { useLoaderData } from 'react-router-dom';

const Trip = () => {
  const menu = useLoaderData();

  return (
    <Wrap spacing="34px" justify="center">
      {menu.data.map((trip) => (
        <WrapItem key={trip.id}><TripCard trip={trip} /> </WrapItem>
      ))}
    </Wrap>
  );
};




// eslint-disable-next-line react-refresh/only-export-components
export async function loader() {
  const menu = await GetTrip();
  return menu;
}


export default Trip;
