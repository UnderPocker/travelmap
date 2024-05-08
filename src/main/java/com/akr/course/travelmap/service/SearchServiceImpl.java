package com.akr.course.travelmap.service;

import com.akr.course.travelmap.double_gis_entities.places_api.*;
import com.akr.course.travelmap.dto_entities.db_entities.Place;
import com.akr.course.travelmap.dto_entities.SearchFilters;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class SearchServiceImpl implements SearchService{
    @Value("${apiKey}")
    private String apiKey;
    private static final String URL = "https://catalog.api.2gis.com/3.0/items";
    @Override
    public List<Place> searchPlaces(SearchFilters searchFilters) {
        Map<String, Object> urlParams = new HashMap<>();

        urlParams.put("region_id", "19");
        urlParams.put("key", apiKey);
        if (searchFilters.getTypes() != null && !searchFilters.getTypes().isEmpty())
            urlParams.put("rubric_id", StringUtils.collectionToDelimitedString(searchFilters.getTypes(), ","));
        urlParams.put("work_time", "now");

        String[] fields = new String[]{"items.point", "items.external_content", "items.rubrics", "items.schedule", "items.reviews", "items.ads.options", "items.context"};
        urlParams.put("fields", StringUtils.collectionToDelimitedString(Arrays.asList(fields), ","));

        if (searchFilters.getWord() != null)
            urlParams.put("q", searchFilters.getWord());

        if (searchFilters.getLon() != null && searchFilters.getLat() != null) {
            urlParams.put("lon", searchFilters.getLon());
            urlParams.put("lat", searchFilters.getLat());
            if (searchFilters.getMaxDistance() == null)
                urlParams.put("radius", 15000);
        }

        if (searchFilters.getPage() != null){
            urlParams.put("page", searchFilters.getPage());
        }

        if (searchFilters.getMaxPrice() != null && searchFilters.getMinPrice() != null)
            urlParams.put("attr[food_service_avg_price]", searchFilters.getMinPrice() + "," + searchFilters.getMaxPrice());

        if (searchFilters.getMaxDistance() != null)
            urlParams.put("radius", searchFilters.getMaxDistance());

        if (searchFilters.getMinRating() != null){
            String ratingFilter = getRatingTag(searchFilters);
            //if (ratingFilter == null), то что?
            urlParams.put("attr[" + ratingFilter + "]", "true");
        }

        if (searchFilters.getSort() != null){
            urlParams.put("sort", searchFilters.getSort());
        }

        List<Item> items = searchItems(URL, urlParams);
        return convertItemsToPlacesDto(items);
    }

    @Override
    public List<Place> searchPlacesById(List<String> ids) {
        return convertItemsToPlacesDto(searchItemsById(ids));
    }

    private static String getRatingTag(SearchFilters searchFilters) {
        if (searchFilters.getMinRating() <= 3.01) {
            return  "rating_rating_not_bad";
        } else if (searchFilters.getMinRating() <= 3.51) {
            return  "rating_rating_nice";
        } else if (searchFilters.getMinRating() <= 4.01) {
            return  "rating_rating_pretty_good";
        } else if (searchFilters.getMinRating() <= 4.51) {
            return  "rating_rating_excellent";
        }
        else if (searchFilters.getMinRating() <= 4.91){
            return "rating_rating_perfect";
        }
        return null;
    }

    @Override
    public List<Place> convertItemsToPlacesDto(List<Item> items) {
        return items.stream().map(this::convertItemToPlaceDto).collect(Collectors.toList());
    }

    @Override
    public Place convertItemToPlaceDto(Item item) {
        Place place = new Place();

        place.setId(item.getId());
        place.setName(item.getName());
        place.setAddress(item.getAddress());
        place.setLon(item.getLon());
        place.setLat(item.getLat());
        place.setType(item.getRubrics() != null ? item.getRubrics()[0].getId() : null);
        place.setRating(item.getReviews().getRating());
        place.setReviews(item.getReviews().getReviewCount());
        place.setLink(item.getLink());
        place.setPhone(item.getPhone());
        place.setWorkTime(item.getTodaySchedule());
        place.setCost(item.getCost());
        place.setDoubleGisLink("https://2gis.ru/n_novgorod/firm/" + item.getId());

        ExternalContent[] externalContents = item.getExternalContents();
        List<String> photos = Arrays.stream(externalContents).map(ExternalContent::getMainPhotoUrl).collect(Collectors.toList());

        place.setPhotos(photos);

        return place;
    }

    @Override
    public List<Item> searchItemsById(List<String> ids) {
        String url = URL + "/byid";

        Map<String, Object> params = new HashMap<>();
        params.put("id", StringUtils.collectionToDelimitedString(ids, ","));
        params.put("region_id", "19");
        params.put("key", apiKey);
        String[] fields = new String[]{"items.point", "items.external_content", "items.rubrics", "items.schedule", "items.reviews", "items.ads.options", "items.context"};
        params.put("fields", StringUtils.collectionToDelimitedString(Arrays.asList(fields), ","));

        return searchItems(url, params);
    }

    @Override
    public List<Item> searchItems(String url, Map<String, Object> params) {
        DoubleGisResponse doubleGisResponse = getDoubleGisResponseFromUrl(url, params);
        Result responseResult = doubleGisResponse.getResult();
        //System.out.println(responseResult);

        if (responseResult == null || responseResult.getItems().isEmpty()){
            if (doubleGisResponse.getMeta().getCode() == 404)
                return Collections.emptyList();
            else
                throw new RuntimeException(doubleGisResponse.getError().getMessage());
        }

        return responseResult.getItems();
    }

    @Override
    public DoubleGisResponse getDoubleGisResponseFromUrl(String url, Map<String, Object> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        for (Map.Entry<String, Object> pair : params.entrySet()){
            String name = pair.getKey();
            Object value =  pair.getValue();
            builder.queryParam(name, value);
        }

        RestTemplate restTemplate = new RestTemplate();

        String query = builder.build().toUriString();
        System.out.println(query);
        //System.out.println(restTemplate.getForObject(query, String.class));
        return restTemplate.getForObject(query, DoubleGisResponse.class);
    }
}