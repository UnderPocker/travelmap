package com.akr.course.travelmap.double_gis_entities;
import lombok.Data;

@Data
public class WorkingHours {
    private String from, to;

    @Override
    public String toString() {
        return from + " - " + to;
    }
}
