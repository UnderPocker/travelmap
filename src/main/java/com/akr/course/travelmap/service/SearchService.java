package com.akr.course.travelmap.service;

import com.akr.course.travelmap.double_gis_entities.DoubleGisResponse;
import com.akr.course.travelmap.double_gis_entities.Item;
import com.akr.course.travelmap.double_gis_entities.Point;
import com.akr.course.travelmap.dto.PlaceDto;
import com.akr.course.travelmap.dto.SearchFilters;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<PlaceDto> searchPlaces(SearchFilters searchFilters);
    Point getItemPoint(String id);
    List<PlaceDto> convertItemsToPlacesDto(List<Item> items);
    PlaceDto convertItemToPlaceDto(Item item);
    Item searchItemById(String id, Map<String, Object> params);
    List<Item> searchItems(Map<String, Object> params);
    DoubleGisResponse getDoubleGisResponseFromUrl(String url, Map<String, Object> params);
}