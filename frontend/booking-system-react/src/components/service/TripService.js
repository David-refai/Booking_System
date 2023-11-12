import axios from 'axios';

export const GetTrip = async () => {
  try {
    // after the proxy is set up in vite.config.js, this will work
  return await axios.get('api/v1/trips/all');
  

  } catch (error) {
    console.log(error.message);
  }

};

