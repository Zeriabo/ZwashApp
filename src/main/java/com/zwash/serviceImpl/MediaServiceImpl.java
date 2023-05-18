package com.zwash.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
import com.zwash.repository.MediaRepository;
import com.zwash.service.MediaService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Media saveMedia(Media media,Station station ) {
    	
    	   // Upload logo file
        if (media.getLogo() != null && media.getLogo()!="") {
            String logoFileName = saveFile(media.getLogoFile());
            station.getMedia().setLogo(logoFileName);
        }

     // Upload picture file
        if (media.getPictureFile() != null && media.getPicture()!="") {
            String pictureFileName = saveFile(media.getPictureFile());
            station.getMedia().setPicture(pictureFileName);
        }
        return mediaRepository.save(media);
    }

    @Override
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMediaById(Long id) {
        mediaRepository.deleteById(id);
    }
    
    private String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path directoryPath = Paths.get("media");
            Path filePath = directoryPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            // Handle file upload exception
            throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
        }
    }

}
