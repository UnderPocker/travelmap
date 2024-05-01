package com.akr.course.travelmap.service;

import com.akr.course.travelmap.double_gis_entities.distance_api.Route;
import com.akr.course.travelmap.dto.Distance;


public interface DistanceService {
    Distance getDistanceBetweenPoints(Double srcLon, Double srcLat, Double destLon, Double destLat);
    Distance convertRouteToDistanceDto(Route route);
}
