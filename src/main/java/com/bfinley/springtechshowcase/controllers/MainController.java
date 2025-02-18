package com.bfinley.springtechshowcase.controllers;

import com.bfinley.springtechshowcase.models.Album;
import com.bfinley.springtechshowcase.models.Photo;
import com.bfinley.springtechshowcase.services.AlbumService;
import com.bfinley.springtechshowcase.services.PhotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    private ArrayList<Photo> photos;

    private ArrayList<Album> albums;

    private String resultsReminder;


    @Autowired
    AlbumService albumService;

    @Autowired
    private PhotoService photoService;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("albums", albumService.getAllAlbums());


        return "index";
    }

    @RequestMapping("/photos")

    public String photos(Model model,
                         @RequestParam(name = "album", required = false) String album,
                         @RequestParam(name = "search", required = false) String search

    ) {

        // If album is present
        if (album != null) {
            // If param is all, get all photos
            if (album.equals("all")) {
                resultsReminder = "All Photos";
                model.addAttribute("photos", photoService.getAllPhotos());
            } else {
                // Otherwise, use the album id to get photos
                resultsReminder = "Photos in Album " + album;
                model.addAttribute("photos", photoService.getPhotosByAlbumId(Long.parseLong(album)));
            }
        }

        // If search is present
        if (search != null) {
            // If search is empty, get all photos
            if (search.isEmpty()) {
                resultsReminder = "All Photos";
            } else {
                // Otherwise, filter photos by search
                resultsReminder = "Search results for: " + search;
            }
            List<Photo> photos = photoService.getAllPhotos();
            List<Photo> filteredPhotos = new ArrayList<>();
            for (Photo photo : photos) {
                // TODO Would need more information RE: multi-word searches here to determine if this is a good
                //  approach, or if we need to tokenize the search and search for all terms, any terms, etc.  May also
                //  need to look at URL decoding
                if (photo.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    filteredPhotos.add(photo);
                }
            }
            model.addAttribute("photos", filteredPhotos);
        }

        model.addAttribute("searchReminder", resultsReminder);
        return "photos";
    }

}
