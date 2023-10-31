import axios from 'axios';

export const GetUser = async () => {
  try {
    // after the proxy is set up in vite.config.js, this will work
      return await axios.get('api/v1/users');
  
  } catch (error) {
    console.log(error);
  }
};
 