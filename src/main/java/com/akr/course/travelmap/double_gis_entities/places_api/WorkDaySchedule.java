package com.akr.course.travelmap.double_gis_entities.places_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDaySchedule {
    @JsonProperty("working_hours")
    private WorkingHours[] workingHours;

    @JsonProperty("is_24x7")
    private boolean isAllDay;

    private String comment;

    public WorkDaySchedule(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public WorkDaySchedule(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return isAllDay ? "24/7" : workingHours != null ? workingHours[0].toString() : comment;
    }
}
