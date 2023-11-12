import {
  Badge,
  Button,
  Input,
  Textarea,
  useDisclosure,
} from '@chakra-ui/react';
import axios from 'axios';
import { useState } from 'react';

const AddTripForm = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [isLoading, setIsLoading] = useState(false);


  const [file, setFile] = useState(null);
  const [trip, setTrip] = useState({
    name: '',
    description: '',
    destination: '',
    duration: '',
    startDate: '',
    endDate: '',
    price: '',
    maxSeats: '',
  });

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setTrip((prevTrip) => ({
      ...prevTrip,
      [name]: value,
    }));
  };

  const submitHandler = async (e) => {
    e.preventDefault();
    const formData = new FormData();

    const tripDetails = {
      name: trip.name,
      description: trip.description,
      destination: trip.destination,
      startDate: trip.startDate,
      endDate: trip.endDate,
      price: trip.price,
      // Add more trip details as needed
    };

    formData.append(
      'trip',
      new Blob([JSON.stringify(tripDetails)], { type: 'application/json' }) // Convert object to JSON string before sending to backend server
      //   new Blob is used for the 'trip' field because it is not a file
    );
    formData.append('files', file);
    try {
      const response = await axios.post('/api/v1/trips/createTrip', formData, {
        headers: {
          'Content-Type': `multipart/form-data`,
          'Content-Length': formData.length,
        },
      });
      console.log('Trip created:', response.data);
    } catch (error) {
      console.error('Error occurred:', error);
    }
  };

  console.log(trip);
  return (
    <form
      onSubmit={submitHandler}
      style={{
        display: 'flex',
        flexDirection: 'column',
        width: '50%',
        margin: 'auto',
        marginTop: '10%',
        padding: '10px',
        boxShadow: '0 0 10px 0 rgba(0,0,0,0.2)',
        borderRadius: '10px',
        backgroundColor: 'white',
      }}
    >
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <div>
          <Badge colorScheme="green" mt="4" mb="2">
            name
          </Badge>
          <Input
            type="text"
            required
            onChange={(e) => handleInputChange(e)}
            name="name"
            value={trip.name}
          />
        </div>
        <div>
          <Badge colorScheme="green" mt="4" mb="2">
            description
          </Badge>
          <Textarea
            required
            onChange={(e) => handleInputChange(e)}
            name="description"
            value={trip.description}
          />
        </div>
        <div>
          <Badge colorScheme="green" mt="4" mb="2">
            destination
          </Badge>
          <Input
            type="text"
            required
            onChange={(e) => handleInputChange(e)}
            name="destination"
            value={trip.destination}
          />
        </div>
        <div>
          <div style={{ display: 'flex', flexDirection: 'column' }}>
            <Badge colorScheme="green" mt="4" mb="2">
              image
            </Badge>
            <input
              type="file"
              accept="image/*"
              onChange={handleFileChange}
              multiple={true}
            />
          </div>
        </div>
        <div>
          <Badge colorScheme="green" mt="4" mb="2">
            duration
          </Badge>
          <Input
            type="number"
            required
            onChange={(e) => handleInputChange(e)}
            name="duration"
            value={trip.duration}
          />
        </div>
        <div
          style={{
            display: 'flex',
            gap: '5px',
            flexDirection: 'column',
            width: '30%',
          }}
        >
          <Badge colorScheme="green" mt="4" mb="2">
            start date
          </Badge>
          <Input
            style={{ width: '100%' }}
            type="date"
            onChange={(e) => handleInputChange(e)}
            name="startDate"
            value={trip.startDate}
          />
     
          <Badge colorScheme="green" mt="4" mb="2">
            end date
          </Badge>
          <Input
            type="date"
            onChange={(e) => handleInputChange(e)}
            name="endDate"
            value={trip.endDate}
          />
        </div>
        <div>
          <Badge colorScheme="green" mt="4" mb="2">
            price
          </Badge>
          <Input
            type="number"
            required
            onChange={(e) => handleInputChange(e)}
            name="price"
            value={trip.price}
          />
        </div>
        <div>
          <Badge colorScheme="green" mt="4" mb="2">
            max seats
          </Badge>
          <Input
            type="number"
            required
            onChange={(e) => handleInputChange(e)}
            name="maxSeats"
            value={trip.maxSeats}
          />
        </div>
      </div>

      <Button
        mt="4"
        type="submit"
        // isLoading={isLoading}
        loadingText="Loading"
        colorScheme="teal"
        variant="outline"
        spinnerPlacement="start"
      >
        Save
      </Button>
    </form>
  );
};

export default AddTripForm;
