package com.akr.course.travelmap.double_gis_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Context {
    @JsonProperty("stop_factors")
    private List<StopFactor> stopFactors;
}
