package com.akr.course.travelmap.double_gis_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
        return isAllDay ? "24/7" : workingHours[0].toString();
    }
}
