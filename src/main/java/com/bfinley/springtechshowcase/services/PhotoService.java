package com.bfinley.springtechshowcase.services;

import com.bfinley.springtechshowcase.models.Album;
import com.bfinley.springtechshowcase.models.Photo;
import com.bfinley.springtechshowcase.utils.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoService {

    @Autowired
    private RequestHelper requestHelper;


    public List<Photo> getAllPhotos() {
        ArrayList<Album> allAlbums = requestHelper.getFullAlbumList();
        // I know the variable is redundant, but I like to keep it separate, especially if there's not a huge
        // performance hit, so I can debug easier
        List<Photo> allPhotos = allAlbums.stream().flatMap(album -> album.getPhotos().stream()).collect(Collectors.toList());
        return allPhotos;
    }

    public List<Photo> getPhotosByAlbumId(Long albumId) {
        // I know the variable is redundant, but I like to keep it separate, especially if there's not a huge
        // performance hit, so I can debug easier

        ArrayList<Photo> photosByAlbum = requestHelper.getByAlbumId(Long.valueOf(albumId));
        return photosByAlbum;
    }


}
