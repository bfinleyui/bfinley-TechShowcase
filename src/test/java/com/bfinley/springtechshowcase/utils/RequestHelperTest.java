package com.bfinley.springtechshowcase.utils;

import com.bfinley.springtechshowcase.models.Album;
import com.bfinley.springtechshowcase.models.Photo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;



import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
        "com.bfinley.apiHeader = lt_api_key",
        "com.bfinley.apiKey = lt_tech_showcase",
        "com.bfinley.apiBaseUrl = https://showcase.leantechniques.com"
})

class RequestHelperTest {

    @Mock
    private HttpClient client;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private RequestHelper requestHelper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        requestHelper.setApiHeader("lt_api_key");
        requestHelper.setApiKey("lt_tech_showcase");
        requestHelper.setBaseUrl("https://showcase.leantechniques.com");
    }

    @Test
    public void testGetFullAlbumList() throws IOException, InterruptedException {
        String json = "[{\"albumId\":2,\"photos\":[{\"photoId\":7,\"url\":\"https://showcase.leantechniques.com/image/welcome.jpg\",\"albumId\":2,\"title\":\"LT Welcome\"},{\"photoId\":8,\"url\":\"https://showcase.leantechniques.com/image/jason-peyton.jpg\",\"albumId\":2,\"title\":\"Jason and Peyton\"},{\"photoId\":3,\"url\":\"https://showcase.leantechniques.com/image/scott-1.jpg\",\"albumId\":2,\"title\":\"Scott Presenting at LT Retreat 2024\"},{\"photoId\":2,\"url\":\"https://showcase.leantechniques.com/image/photo-2.jpg\",\"albumId\":2,\"title\":\"The Team\"},{\"photoId\":9,\"url\":\"https://showcase.leantechniques.com/image/gifford.jpg\",\"albumId\":2,\"title\":\"Tim Gifford, Chief Innovation Officer / Printer Repair Tech\"},{\"photoId\":4,\"url\":\"https://showcase.leantechniques.com/image/danielle.jpg\",\"albumId\":2,\"title\":\"Danielle Brommer, Chief Growth Officer\"},{\"photoId\":6,\"url\":\"https://showcase.leantechniques.com/image/emma.jpg\",\"albumId\":2,\"title\":\"Emma at the Retreat\"},{\"photoId\":5,\"url\":\"https://showcase.leantechniques.com/image/jarred.jpg\",\"albumId\":2,\"title\":\"Jarred Presenting at LT Retreat 2024\"}]},{\"albumId\":3,\"photos\":[{\"photoId\":10,\"url\":\"https://showcase.leantechniques.com/image/roy.jpg\",\"albumId\":3,\"title\":\"Roy ten Brink, One of two interns who made this very API\"},{\"photoId\":11,\"url\":\"https://showcase.leantechniques.com/image/nick.jpg\",\"albumId\":3,\"title\":\"Nick Neely and Batman, One of two interns who made this very API, Batman didn't help\"}]},{\"albumId\":4,\"photos\":[{\"photoId\":12,\"url\":\"https://showcase.leantechniques.com/image/ward.jpg\",\"albumId\":4,\"title\":\"Daniel Ward presenting on the history of software development\"},{\"photoId\":13,\"url\":\"https://showcase.leantechniques.com/image/photo.jpg\",\"albumId\":4,\"title\":\"Christopher Avery speaking about the Responsibility Process\"}]},{\"albumId\":1,\"photos\":[{\"photoId\":1,\"url\":\"https://showcase.leantechniques.com/image/photo-10.jpg\",\"albumId\":1,\"title\":\"Work From Anywhere\"}]}]";

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(json);
        when(client.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        ArrayList<Album> albums = requestHelper.getFullAlbumList();

        // Make sure it's not null
        assertNotNull(albums);
        // Make sure it's getting all 4 albums
        assertEquals(4, albums.size());
        // Make sure the sort works
        assertEquals(1, albums.get(0).getAlbumId());

        // Other possible tests
        // Verify total number of photos
        // Verify nulls are handled
        // Verify exceptions are thrown in negative conditions

    }

    @Test
    public void testGetPhotosByAlbumId() throws IOException, InterruptedException {
        String json = "[{\"photoId\":7,\"url\":\"https://showcase.leantechniques.com/image/welcome.jpg\",\"albumId\":2,\"title\":\"LT Welcome\"},{\"photoId\":8,\"url\":\"https://showcase.leantechniques.com/image/jason-peyton.jpg\",\"albumId\":2,\"title\":\"Jason and Peyton\"},{\"photoId\":3,\"url\":\"https://showcase.leantechniques.com/image/scott-1.jpg\",\"albumId\":2,\"title\":\"Scott Presenting at LT Retreat 2024\"},{\"photoId\":2,\"url\":\"https://showcase.leantechniques.com/image/photo-2.jpg\",\"albumId\":2,\"title\":\"The Team\"},{\"photoId\":9,\"url\":\"https://showcase.leantechniques.com/image/gifford.jpg\",\"albumId\":2,\"title\":\"Tim Gifford, Chief Innovation Officer / Printer Repair Tech\"},{\"photoId\":4,\"url\":\"https://showcase.leantechniques.com/image/danielle.jpg\",\"albumId\":2,\"title\":\"Danielle Brommer, Chief Growth Officer\"},{\"photoId\":6,\"url\":\"https://showcase.leantechniques.com/image/emma.jpg\",\"albumId\":2,\"title\":\"Emma at the Retreat\"},{\"photoId\":5,\"url\":\"https://showcase.leantechniques.com/image/jarred.jpg\",\"albumId\":2,\"title\":\"Jarred Presenting at LT Retreat 2024\"}]";

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(json);
        when(client.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        ArrayList<Photo> photos = requestHelper.getByAlbumId(2L);

        // Make sure it's not null
        assertNotNull(photos);

        // Make sure it's getting all 8 photos
        assertEquals(8, photos.size());

        // Make sure the sort works
        assertEquals(2, photos.get(0).getPhotoId());

    }


}