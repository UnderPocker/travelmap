package com.akr.course.travelmap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchFilters {
    private String locale; //(ru/en)
    private String sort; //(relevance/distance/rating)
    private Integer page;
    private String word;
    private List<Integer> types;
    private List<Integer> prices;
    private String lon, lat;
    private Double minRating;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer maxDistance;
}