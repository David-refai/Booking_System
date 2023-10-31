import axios from 'axios';

export const GetTrip = async () => {
  try {
    // after the proxy is set up in vite.config.js, this will work
    return await axios.get('api/v1/trips/');
  } catch (error) {
    console.log(error.message);
  }

};
export const GetTripImage = async (id) => {
  try {
    // after the proxy is set up in vite.config.js, this will work
    const res = await axios.get(`api/v1/trips/${id}/images`);
    return res;
  } catch (error) {
    console.log(error.message);
  }

}
