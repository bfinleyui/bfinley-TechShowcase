package com.bfinley.springtechshowcase.models;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class Photo {

    public Long photoId;
    public String title;
    public Long albumId;
    public String url;

}
