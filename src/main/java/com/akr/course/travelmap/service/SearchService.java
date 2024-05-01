package com.akr.course.travelmap.service;

import com.akr.course.travelmap.double_gis_entities.places_api.DoubleGisResponse;
import com.akr.course.travelmap.double_gis_entities.places_api.Item;
import com.akr.course.travelmap.dto.Place;
import com.akr.course.travelmap.dto.SearchFilters;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Place> searchPlaces(SearchFilters searchFilters);
    List<Place> searchPlacesById(List<String> ids);
    List<Place> convertItemsToPlacesDto(List<Item> items);
    Place convertItemToPlaceDto(Item item);
    List<Item> searchItemsById(List<String> ids);
    List<Item> searchItems(String url, Map<String, Object> params);
    DoubleGisResponse getDoubleGisResponseFromUrl(String url, Map<String, Object> params);
}