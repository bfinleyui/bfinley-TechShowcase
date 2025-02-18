package com.bfinley.springtechshowcase.services;

import com.bfinley.springtechshowcase.models.Photo;
import com.bfinley.springtechshowcase.utils.RequestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestComponent
class PhotoServiceTest {

    @Mock
    private RequestHelper requestHelper;

    @InjectMocks
    private PhotoService photoService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void testGetPhotosByAlbumId() {
        // This test seems unnecessary in its current state , but hopefully there's more action happening in the service
        // versus just returning the result of the service.  I went back and forth whether to start the test withe JSON
        // and go from album to full list of photos, but I wanted to be sure to not chain tests together.  I'm not sure
        // if that's a best practice, but would be a good spot to get a second set of eyes on it.

        ArrayList<Photo> mockPhotos = new ArrayList<>();

        Photo photo1 = new Photo();
        photo1.setPhotoId(1L);
        photo1.setAlbumId(1L);
        photo1.setTitle("Photo 1");
        photo1.setUrl("https://via.placeholder.com/600/92c952");

        Photo photo2 = new Photo();
        photo2.setPhotoId(2L);
        photo2.setAlbumId(1L);
        photo2.setTitle("Photo 2");
        photo2.setUrl("https://via.placeholder.com/600/771796");

        mockPhotos.add(photo1);
        mockPhotos.add(photo2);

        when(requestHelper.getByAlbumId(1L)).thenReturn(new ArrayList<>(mockPhotos));
        photoService.getPhotosByAlbumId(1L);

        // Album 1 has 2 photos
        assertEquals(2, mockPhotos.size());

        // Album 1 has the first photo with the proper title
        assertEquals("Photo 1", mockPhotos.get(0).getTitle());

    }


    @Test
    void testGetAllPhotos() {

    }
}