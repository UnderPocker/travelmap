package com.akr.course.travelmap.controller;


import com.akr.course.travelmap.dto.PlaceDto;
import com.akr.course.travelmap.dto.SearchFilters;
import com.akr.course.travelmap.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name="main-controller")
public class MyController {
    private SearchService searchService;

    @Operation(
            summary = "Поиск мест в городе",
            description = "Получает объект SearchFilters и на его основе состовляет запрос для 2gis api"
    )

    @GetMapping("/search")
    public List<PlaceDto> searchPlace(SearchFilters searchFilters){
        return searchService.searchPlaces(searchFilters);
    }

    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}