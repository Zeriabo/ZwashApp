package com.zwash.service;

import org.springframework.web.multipart.MultipartFile;

import com.zwash.pojos.Media;
import com.zwash.pojos.Station;

public interface MediaService {
    Media saveMedia(Media media, Station station);
    Media getMediaById(Long id);
    void deleteMediaById(Long id);
}
