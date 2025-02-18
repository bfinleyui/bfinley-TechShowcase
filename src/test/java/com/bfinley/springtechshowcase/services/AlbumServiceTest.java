package com.bfinley.springtechshowcase.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestComponent
class AlbumServiceTest {


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