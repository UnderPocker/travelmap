package com.akr.course.travelmap.controller;


import com.akr.course.travelmap.dto_entities.Distance;
import com.akr.course.travelmap.dto_entities.db_entities.Place;
import com.akr.course.travelmap.dto_entities.SearchFilters;
import com.akr.course.travelmap.dto_entities.db_entities.Route;
import com.akr.course.travelmap.service.DistanceService;
import com.akr.course.travelmap.service.RouteService;
import com.akr.course.travelmap.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Tag(name="main-controller")
public class MyController {
    private SearchService searchService;
    private DistanceService distanceService;
    private RouteService routeService;

    @Operation(
            summary = "Поиск мест в городе",
            description = "Получает объект SearchFilters и на его основе состовляет запрос для 2gis api"
    )

    @GetMapping("/search")
    public List<Place> searchPlace(SearchFilters searchFilters){
        return searchService.searchPlaces(searchFilters);
    }

    @Operation(
            summary = "Получение расстояния и времени между двумя точками"
    )
    @GetMapping("/distance")
    public Distance getDistance(Double srcLon, Double srcLat, Double destLon, Double destLat){
        return distanceService.getDistanceBetweenPoints(srcLon, srcLat, destLon, destLat);
    }

    @Operation(
            summary = "Поиск объектов по id"
    )
    @GetMapping("/search/byid")
    public List<Place> searchBytId(@RequestParam List<String> ids){
        return searchService.searchPlacesById(ids);
    }

    @Operation(
            summary = "Получение рекомендуемых маршрутов"
    )
    @GetMapping("/routes")
    public List<Route> getRecommendedRoutes(){
        return routeService.getAllRecommendedRoutes();
    }

    @GetMapping("routes/add")
    public void putRecommendedRoute(String title, String description,@RequestParam List<String> ids){
        routeService.putRecommendedRoute(title, description, ids);
    }

    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
    @Autowired
    public void setDistanceService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }
    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }
}