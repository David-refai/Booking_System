import { useEffect, useState } from 'react';
import { GetTrip, GetTripImage } from '../service/TripService';
import TripCard from '../view/trip/TripCard';
import { Wrap, WrapItem } from '@chakra-ui/react';

const Trip = () => {
  const [trips, setTrips] = useState([]);
  const [tripImages, setTripImages] = useState([]);

  useEffect(() => {
    GetTrip().then((response) => {
      setTrips(response.data);
    });
  }, []);

  useEffect(() => {
    const fetchTripImages = async () => {
     trips.forEach((trip) => {
         GetTripImage(trip.id).then((response) => {
          setTripImages((prevImages) => [
            ...prevImages,
            { tripId: trip.id, images:  response?.data },
          ]);
        });
      });
    };

    if (trips.length > 0) {
      fetchTripImages();
    }
  }, [trips]);

 

return (
  <div>
    <Wrap spacing="34px" justify="center">
      {trips.map((trip) => (
        <WrapItem key={trip.id}>
          <TripCard
            trip={trip}
          />
        </WrapItem>
      ))}
    </Wrap>
  </div>
);
}
export default Trip;
