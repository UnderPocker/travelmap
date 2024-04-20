package com.akr.course.travelmap.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Сущность фильтра для поиска мест")
public class SearchFilters {
    @Schema(allowableValues = {"ru", "en"}, description = "Язык", example = "ru")
    private String locale;
    @Schema(allowableValues = {"relevance", "distance", "rating"}, example = "relevance", nullable = true)
    private String sort;
    private Integer page;
    private String word;
    @Schema(example = "161")
    private List<Integer> types;
    private String lon, lat;
    private Double minRating;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer maxDistance;
}