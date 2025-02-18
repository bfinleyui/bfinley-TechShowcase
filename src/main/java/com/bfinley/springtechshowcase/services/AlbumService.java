package com.bfinley.springtechshowcase.services;

import com.bfinley.springtechshowcase.models.Album;
import com.bfinley.springtechshowcase.utils.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumService {

    @Autowired
    private RequestHelper requestHelper;

    public List<Long> getAllAlbums() {
        // I know the variable is redundant, but I like to keep it separate, especially if there's not a huge
        // performance hit, so I can debug easier
        List<Long> allAlbums = requestHelper.getFullAlbumList().stream().map(Album::getAlbumId).collect(Collectors.toList());
        return allAlbums;
    }



}
