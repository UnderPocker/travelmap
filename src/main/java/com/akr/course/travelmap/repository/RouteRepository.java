package com.akr.course.travelmap.repository;

import com.akr.course.travelmap.dto_entities.db_entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
