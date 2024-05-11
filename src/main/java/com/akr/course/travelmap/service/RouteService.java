package com.akr.course.travelmap.service;

import com.akr.course.travelmap.dto_entities.db_entities.Route;

import java.util.List;

public interface RouteService {
    List<Route> getAllRecommendedRoutes();
}
