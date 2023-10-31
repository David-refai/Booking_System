package com.example.bookingsystem.repository.image;


import com.example.bookingsystem.dto.ImageDto;
import com.example.bookingsystem.entities.Image;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImageRepositoryImp{

    private ImageRepository imageRepository;

    public ImageRepositoryImp(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

public Image updateImage(Long id, ImageDto imageDto) {
      Optional<Image> image =  imageRepository.findById(id);
        if (image.isPresent()){
            Image img = image.get();
            img.setImageUrl(imageDto.getImageUrl());
            img.setImageName(imageDto.getImageName());
            img.setImageData(imageDto.getImageData());

            return imageRepository.save(img);
        }
        return null;
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void saveImageUrl(Image image) {
        imageRepository.save(image);
    }

    public Image findById(Long imageId) {
        return imageRepository.findById(imageId).orElse(null);
    }
}
