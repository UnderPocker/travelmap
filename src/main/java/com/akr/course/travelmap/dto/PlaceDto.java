package com.akr.course.travelmap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private String id;
    private String lon, lat;
    private List<String> photos;
    private String name;
    private String type;
    private String address;
    private String workTime;
    private String cost;
    private Double rating;
    private Integer reviews;
    private String link;
    private String phone;
    private Boolean favorite;
}