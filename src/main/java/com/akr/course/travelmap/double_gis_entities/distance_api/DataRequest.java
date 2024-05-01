package com.akr.course.travelmap.double_gis_entities.distance_api;

import com.akr.course.travelmap.double_gis_entities.Point;
import lombok.Data;


@Data
public class DataRequest {
    private Point[] points;
    private Integer[] sources = new Integer[]{0};
    private Integer[] targets = new Integer[]{1};
    private String mode = "walking";

    public DataRequest(Point src, Point dest){
        points = new Point[]{src, dest};
    }
}
