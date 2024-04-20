package com.akr.course.travelmap.double_gis_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Item {
    private String id;

    private String name;

    private Point point;

    @JsonProperty("address_name")
    private String address;

    @JsonProperty("external_content")
    private ExternalContent[] externalContents;

    private Rubric[] rubrics;

    private Reviews reviews;

    private Ads ads;

    private Map<String, WorkDaySchedule> schedule;
    private Context context;

    public String getLon(){
        return point.getLon();
    }
    public String getLat(){
        return point.getLat();
    }
    public String getLink(){
       if (ads == null || ads.getOptions().getActions() == null)
           return null;

       List<Action> actions = ads.getOptions().getActions();
       return actions.stream().filter(action -> action.getType().equals("link")).findFirst().map(Action::getValue).orElse(null);
    }
    public String getPhone(){
        if (ads == null || ads.getOptions().getActions() == null)
            return null;

        List<Action> actions = ads.getOptions().getActions();
        return actions.stream().filter(action -> action.getType().equals("phone")).findFirst().map(Action::getValue).orElse(null);
    }

    public String getTodaySchedule(){
        if (schedule.containsKey("is_24x7") && schedule.get("is_24x7").isAllDay())
            return schedule.get("is_24x7").toString();

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        String dayOfWeekAbbreviation = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

        return schedule.get(dayOfWeekAbbreviation).toString();
    }

    public String getCost(){
        if (context.getStopFactors() == null)
            return null;
        for (StopFactor stopFactor : context.getStopFactors()) {
            if (stopFactor.getTag() != null && stopFactor.getTag().equals("food_service_avg_price")){
                String name = stopFactor.getName();

                Pattern costPattern = Pattern.compile("Средний чек (\\d+).*₽");
                Matcher costMatcher = costPattern.matcher(name);

                if (costMatcher.find()){
                    return costMatcher.group(1);
                }
            }
        }

        return null;
    }

}
