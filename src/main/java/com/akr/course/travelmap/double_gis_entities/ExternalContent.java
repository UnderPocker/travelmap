package com.akr.course.travelmap.double_gis_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalContent {
    @JsonProperty("main_photo_url")
    private String mainPhotoUrl;
}
