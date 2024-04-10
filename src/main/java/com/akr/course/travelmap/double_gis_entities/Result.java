package com.akr.course.travelmap.double_gis_entities;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private List<Item> items;
    private int total;
}
