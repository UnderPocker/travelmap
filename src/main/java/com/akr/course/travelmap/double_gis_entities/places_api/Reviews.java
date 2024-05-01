package com.akr.course.travelmap.double_gis_entities.places_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Reviews {
    @JsonProperty("general_rating")
    private Double rating;

    @JsonProperty("general_review_count_with_stars")
    private Integer reviewCount;
}
