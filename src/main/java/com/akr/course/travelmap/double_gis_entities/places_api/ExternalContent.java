package com.akr.course.travelmap.double_gis_entities.places_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalContent {
    @JsonProperty("main_photo_url")
    private String mainPhotoUrl;
}
