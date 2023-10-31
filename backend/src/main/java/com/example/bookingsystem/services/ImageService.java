package com.example.bookingsystem.services;

import com.example.bookingsystem.dto.ImageDto;
import com.example.bookingsystem.entities.Image;
import com.example.bookingsystem.repository.image.ImageRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepositoryImp imageRepository;

    public String saveImage(MultipartFile file, String url) {
        try {
            // Create an entity to store the image data
            Image imageEntity = new Image();
//             save the image from url to the entity
            imageEntity.setImageUrl(url);


            imageEntity.setImageData(file.getBytes()); // Store the image as bytes

            // Set other properties if needed (e.g., image name, type, etc.)
            imageEntity.setImageName(file.getOriginalFilename());
            // Set more properties as required...

            // Save the image to database
            imageRepository.saveImage(imageEntity);

        } catch (IOException e) {
            // Handle the exception, for example, log it or return an error message
        }
        return url;
    }

    public List<Image> getAllImages() {
        return imageRepository.getAllImages();
    }

    public void saveImageUrl(String image) {
        Image imageEntity = new Image();
        imageEntity.setImageUrl(image);
        imageRepository.saveImageUrl(imageEntity);
    }

    public Image updateImage(Long id, ImageDto imageDto) {
        return imageRepository.updateImage(id, imageDto);
    }

    public Image findById(Long imageId) {
        return imageRepository.findById(imageId);
    }
}

