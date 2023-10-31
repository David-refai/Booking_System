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
// import { BsStar, BsStarFill, BsStarHalf } from 'react-icons/bs';
// import { FiShoppingCart } from 'react-icons/fi';

// const data = {
//   isNew: true,
//   imageURL:
//     'https://images.unsplash.com/photo-1572635196237-14b3f281503f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=4600&q=80',
//   name: 'Wayfarer Classic',
//   price: 4.5,
//   rating: 4.2,
//   numReviews: 34,
// };

// function Rating({ rating, numReviews }) {
//   return (
//     <Box display="flex" alignItems="center">
//       {Array(5)
//         .fill('')
//         .map((_, i) => {
//           const roundedRating = Math.round(rating * 2) / 2;
//           if (roundedRating - i >= 1) {
//             return (
//               <BsStarFill
//                 key={i}
//                 style={{ marginLeft: '1' }}
//                 color={i < rating ? 'teal.500' : 'gray.300'}
//               />
//             );
//           }
//           if (roundedRating - i === 0.5) {
//             return <BsStarHalf key={i} style={{ marginLeft: '1' }} />;
//           }
//           return <BsStar key={i} style={{ marginLeft: '1' }} />;
//         })}
//       <Box as="span" ml="2" color="gray.600" fontSize="sm">
//         {numReviews} review{numReviews > 1 ? 's' : ''}
//       </Box>
//     </Box>
//   );
// }

function TripCard({ trip }) {
  const img = trip.images.map((image) => {
    return image.imageUrl;
  });

  console.log(img);

  const {
    id,
    name,
    price,
    description,
    destination,
    end_data,
    start_date,
    status,
  } = trip;

  return (
    <Box
      bg={useColorModeValue('white', 'gray.800')}
      maxW="sm"
      flexWrap={{ base: 'wrap', md: 'nowrap' }}
      borderWidth="1px"
      rounded="lg"
      shadow="lg"
      position="relative"
    >

      <Image
        // if image is null, display a default image avatar
        src={img[0] ? img[0] : 'https://bit.ly/2Z4KKcF'}
        alt={`Picture of ${trip.name}`}
        w={{ base: 'sm', md: 'sm' }}
        h="250px"
        roundedTop="lg"
      />

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
