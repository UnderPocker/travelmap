package com.akr.course.travelmap.dto_entities;

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
    @Schema(description = "0 - диапазон не выбран, далее чем выше priceRange, тем выше сдвигается дипазон, при \"4\" - до 4500р", allowableValues = {"0", "1", "2", "3", "4"}, example = "2", nullable = true)
    private Integer priceRange;
    private Integer maxDistance;
    @Schema(description = "Работает ли сейчас заведение")
    private Boolean isWorkingNow;
}