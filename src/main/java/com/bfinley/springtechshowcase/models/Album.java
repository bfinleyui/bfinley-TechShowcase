package com.bfinley.springtechshowcase.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class Album {

    public Long albumId;
    public ArrayList<Photo> photos;

}
