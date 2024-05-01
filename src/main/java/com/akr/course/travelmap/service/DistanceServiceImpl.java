package com.akr.course.travelmap.service;

import com.akr.course.travelmap.double_gis_entities.Point;
import com.akr.course.travelmap.double_gis_entities.distance_api.DataRequest;
import com.akr.course.travelmap.double_gis_entities.distance_api.DoubleGisResponse;
import com.akr.course.travelmap.double_gis_entities.distance_api.Route;
import com.akr.course.travelmap.dto.Distance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
@Service
public class DistanceServiceImpl implements DistanceService{
    @Value("${apiKey}")
    private String apiKey;
    private static final String URL = "https://routing.api.2gis.com/get_dist_matrix";
    @Override
    public Distance getDistanceBetweenPoints(Double srcLon, Double srcLat, Double destLon, Double destLat) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        builder.queryParam("key", apiKey);

        String query = builder.build().toUriString();
        DataRequest dataRequest = new DataRequest(new Point(srcLon, srcLat), new Point(destLon, destLat));

        RestTemplate restTemplate = new RestTemplate();
        DoubleGisResponse doubleGisResponse = restTemplate.postForObject(query, dataRequest, DoubleGisResponse.class);
        Route route = Objects.requireNonNull(doubleGisResponse).getRoutes().get(0);

        return convertRouteToDistanceDto(route);
    }

    @Override
    public Distance convertRouteToDistanceDto(Route route) {
        return new Distance(route.getDistance(), route.getDuration() / 60, route.getStatus().equals("OK"));
    }
}
