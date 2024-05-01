package com.akr.course.travelmap.double_gis_entities.places_api;

import lombok.Data;

@Data
public class DoubleGisResponse {
    private Result result;
    private Meta meta;

    public Error getError(){
        return meta.getError();
    }
}
