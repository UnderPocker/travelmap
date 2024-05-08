package com.akr.course.travelmap.service;

import com.akr.course.travelmap.dto_entities.db_entities.Place;
import com.akr.course.travelmap.dto_entities.db_entities.Route;
import com.akr.course.travelmap.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;
    private SearchService searchService;

    @Override
    public List<Route> getAllRecommendedRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public void putRecommendedRoute(String title, String description, List<String> ids) {
        Route route = new Route();
        route.setTitle(title);
        route.setDescription(description);
        List<Place> places = searchService.searchPlacesById(ids);
        route.setPlaces(places);
        routeRepository.saveAndFlush(route);
    }

    @Autowired
    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
