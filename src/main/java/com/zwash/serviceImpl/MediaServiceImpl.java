package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
import com.zwash.repository.MediaRepository;
import com.zwash.service.MediaService;

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
        if (media.getLogo() != null ) {
            String logoFileName = saveFile(media.getLogo());
            station.getMedia().setLogo(logoFileName);
        }

     // Upload picture file
        if (pictureFile != null && !pictureFile.isEmpty()) {
            String pictureFileName = saveFile(pictureFile);
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
            Path filePath = Path.of(uploadDirectory, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            // Handle file upload exception
            throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
        }
    }

}
