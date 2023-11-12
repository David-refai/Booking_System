// Desc: This file contains the TripCard component. This component is used to display a single trip card.
// import React from 'react';

import {
  Flex,
  // Circle,
  Box,
  Image,
  Badge,
  useColorModeValue,
  // Icon,
  Tooltip,
  Tag,
} from '@chakra-ui/react';



function TripCard({ trip }) {


  const {id, name, price, start_date } = trip;
console.log(trip);
  const extractBase64Image = (dataURL) => {
    if (dataURL) {
      const splitDataURL = dataURL.split(',');
      const matchType = splitDataURL[0].match(/:(.*?);/);
      const type = matchType ? matchType[1] : ''; // Check for matchType before accessing index

      const base64Data = splitDataURL[0];
      return {
        type,
        data: base64Data,
      };
    }
  };

  return (
    <Box
      key={id}
      bg={useColorModeValue('white', 'gray.800')}
      maxW="sm"
      flexWrap={{ base: 'wrap', md: 'nowrap' }}
      borderWidth="1px"
      rounded="lg"
      shadow="lg"
      position="relative"
    >
      {trip.images?.map((imageData, index) => {
        return (
          <Image
            key={index}
            src={
              trip.images.length > 0
                ? `data:${
                    extractBase64Image(imageData.imageData).type
                  };base64,${extractBase64Image(imageData.imageData).data}`
                : 'https://picsum.photos/200/300'
            }
            alt={`Picture ${index + 1} of ${trip.name}`}
            w={{ base: 'sm', md: 'sm' }}
            h="200px"
            roundedTop="lg"
          />
        );
      })}

      <Box p="8">
        <Box display="flex" alignItems="baseline">
          {start_date && (
            <Badge rounded="full" px="2" fontSize="0.8em" colorScheme="red">
              {start_date ? 'New' : null}
            </Badge>
          )}
        </Box>
        <Flex mt="1" justifyContent="space-between" alignContent="center">
          <Box
            fontSize="2xl"
            fontWeight="semibold"
            as="h4"
            lineHeight="tight"
            isTruncated
          >
            {name}
          </Box>
          <Tooltip
            label="Add to cart"
            bg="white"
            placement="top"
            color="gray.800"
            fontSize="1.2em"
          >
            <a href="#" style={{ display: 'flex' }}>
              {/* <Icon as={FiShoppingCart} h={7} w={7} alignSelf="center" /> */}
            </a>
          </Tooltip>
        </Flex>

        <Flex justifyContent="space-between" alignContent="center">
          {/* <Rating rating={data.rating} numReviews={data.numReviews} /> */}
          <Box fontSize="2xl" color={useColorModeValue('gray.800', 'white')}>
            <Box as="span" color="gray.600" fontSize="lg"></Box>
            {Math.floor(price) + ' SEK'}
            <Tag
              ml="1"
              size="sm"
              colorScheme="green"
              variant="solid"
              marginLeft={2}
            >
              par person
            </Tag>
          </Box>
        </Flex>
      </Box>
    </Box>
  );
}



export default TripCard;
