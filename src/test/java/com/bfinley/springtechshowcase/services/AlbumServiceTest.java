package com.bfinley.springtechshowcase.services;

import com.bfinley.springtechshowcase.models.Album;
import com.bfinley.springtechshowcase.models.Photo;
import com.bfinley.springtechshowcase.utils.RequestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestComponent
class AlbumServiceTest {

    @Mock
    private RequestHelper requestHelper;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetAllAlbums() {
        ArrayList<Long> mockAlbums = new ArrayList<>(Arrays.asList(1L, 2L));

        when(albumService.getAllAlbums()).thenReturn(mockAlbums);

        // Got all albums
        assertEquals(2, mockAlbums.size());

        // Album 1 has 2 photos
        assertEquals(2L, mockAlbums.get(1));
    }




}