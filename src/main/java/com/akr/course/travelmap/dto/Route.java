package com.akr.course.travelmap.dto;

import java.util.Date;
import java.util.List;

public class Route {
    private String id;
    private String name;
    private String cost;
    private String desc;
    private String imageUrl;
    private boolean favourite;
    private double rating;
    private List<PlaceDto> placeDtos;
    private List<Integer> timeToWalk;
    private Date wasTaken;
}
