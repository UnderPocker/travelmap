package com.akr.course.travelmap.controller;


import com.akr.course.travelmap.dto.PlaceDto;
import com.akr.course.travelmap.dto.SearchFilters;
import com.akr.course.travelmap.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {
    private SearchService searchService;
    @GetMapping("/search")
    public List<PlaceDto> searchPlace(SearchFilters searchFilters){
        return searchService.searchPlaces(searchFilters);
    }

    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}