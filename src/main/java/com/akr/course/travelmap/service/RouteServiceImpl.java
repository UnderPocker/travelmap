package com.akr.course.travelmap.service;

import com.akr.course.travelmap.dto_entities.db_entities.Route;
import com.akr.course.travelmap.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;

    @Override
    public List<Route> getAllRecommendedRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRecommendedRoute(Integer id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        return optionalRoute.orElse(null);
    }

    @Autowired
    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
}
