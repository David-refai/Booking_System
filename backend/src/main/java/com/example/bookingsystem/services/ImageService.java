package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.Image;
import com.example.bookingsystem.repository.image.ImageRepo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class ImageService {

    private final ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public List<Image> findAll() {
        return imageRepo.findAll();
    }

    public List<Image> getImagesByTripId(Long id) {
        return imageRepo.getImagesByTripId(id);
    }


    public void resizeAndSaveImage(MultipartFile file, String savePath, int targetWidth) {
        try {
            if (file != null && !file.isEmpty()) {
                // Create a temporary file
                File tempFile = File.createTempFile("temp", null);
                file.transferTo(tempFile);

                // Check a file format
                String fileName = file.getOriginalFilename();
                if (fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif"))) {
                    // Resize the image using Thumbnailator
                    Thumbnails.of(tempFile)
                            .size(targetWidth, targetWidth) // Change targetWidth to desired width
                            .toFile(new File(savePath));

                    // Delete the temporary file
                    tempFile.delete();
                } else {
                    System.out.println("Unsupported file format.");
                    // Handle unsupported file formats accordingly
                    tempFile.delete(); // Delete the temporary file
                }
            } else {
                System.out.println("File is null or empty. Cannot process the image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
