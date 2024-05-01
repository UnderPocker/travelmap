package com.akr.course.travelmap.double_gis_entities.places_api;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private List<Item> items;
    private int total;
}
