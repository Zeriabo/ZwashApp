package com.zwash.service;

import com.zwash.exceptions.MediaNotExistsException;
import com.zwash.pojos.Media;
import com.zwash.pojos.Station;

public interface MediaService {
    Media saveMedia(Media media, Station station);
    Media getMediaById(Long id) throws MediaNotExistsException;
    void deleteMediaById(Long id);
}
