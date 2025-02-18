package com.bfinley.springtechshowcase.utils;

import com.bfinley.springtechshowcase.models.Album;
import com.bfinley.springtechshowcase.models.Photo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

@Component
@Data
public class RequestHelper {

    // Again, these could be stored in a number of waves, ENV vars, s3 secrets, command line args, etc.
    @Value("${com.bfinley.apiKey}")
    private String apiKey;

    @Value(("${com.bfinley.apiHeader}"))
    private String apiHeader;

    @Value(("${com.bfinley.apiBaseUrl}"))
    private String baseUrl;

    private HttpClient client = HttpClient.newHttpClient();


    public RequestHelper() {
    }

    public ArrayList<Album> getFullAlbumList() {
        String url = baseUrl + "/albums";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(apiHeader, apiKey)
                .header("accept", "application/json")
                .build();


        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200) {

                Gson gson = new Gson();
                Type albumListType = new TypeToken<ArrayList<Album>>() {}.getType();

                ArrayList<Album> albums = gson.fromJson(response.body(), albumListType);
                albums.sort(Comparator.comparing(Album::getAlbumId));
                return albums;
            } else {
                return null;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public ArrayList<Photo> getByAlbumId(Long albumId) {
        String url = baseUrl + "/albums/" + albumId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(apiHeader, apiKey)
                .header("accept", "application/json")
                .build();


        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                Type photoListType = new TypeToken<ArrayList<Photo>>() {}.getType();

                ArrayList<Photo> photos = gson.fromJson(response.body(), photoListType);
                photos.sort(Comparator.comparing(Photo::getPhotoId));
                return photos;
            } else {
                return null;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public AbstractMap<String, String> buildHeaders() {
        return new HashMap<String, String>() {{
            put("apiHeader", apiHeader);
            put("apiKey", apiKey);
            put("accept", "application/json");
        }};
    }


}
