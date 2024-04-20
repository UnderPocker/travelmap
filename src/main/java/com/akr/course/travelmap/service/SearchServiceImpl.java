package com.akr.course.travelmap.service;

import com.akr.course.travelmap.double_gis_entities.*;
import com.akr.course.travelmap.dto.PlaceDto;
import com.akr.course.travelmap.dto.SearchFilters;
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
    @Override
    public List<PlaceDto> searchPlaces(SearchFilters searchFilters) {
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

        List<Item> items = searchItems(urlParams);
        return convertItemsToPlacesDto(items);
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
    public Point getItemPoint(String id) {
        Map<String, Object> params = new HashMap<>();

        params.put("key", apiKey);
        params.put("fields", "items.point");

        Item item = searchItemById(id, params);

        return item.getPoint();
    }

    @Override
    public List<PlaceDto> convertItemsToPlacesDto(List<Item> items) {
        return items.stream().map(this::convertItemToPlaceDto).collect(Collectors.toList());
    }

    @Override
    public PlaceDto convertItemToPlaceDto(Item item) {
        PlaceDto placeDto = new PlaceDto();

        placeDto.setId(item.getId());
        placeDto.setName(item.getName());
        placeDto.setAddress(item.getAddress());
        placeDto.setLon(item.getLon());
        placeDto.setLat(item.getLat());
        placeDto.setType(item.getRubrics()[0].getId());
        placeDto.setRating(item.getReviews().getRating());
        placeDto.setReviews(item.getReviews().getReviewCount());
        placeDto.setLink(item.getLink());
        placeDto.setPhone(item.getPhone());
        placeDto.setWorkTime(item.getTodaySchedule());
        placeDto.setCost(item.getCost());
        placeDto.setDoubleGisLink("https://2gis.ru/n_novgorod/firm/" + item.getId());

        ExternalContent[] externalContents = item.getExternalContents();
        List<String> photos = Arrays.stream(externalContents).map(ExternalContent::getMainPhotoUrl).collect(Collectors.toList());

        placeDto.setPhotos(photos);

        return placeDto;
    }

    @Override
    public Item searchItemById(String id, Map<String, Object> params) {
        String url = "https://catalog.api.2gis.com/3.0/items/byid";
        params.put("id", id);
        Result responseResult = getDoubleGisResponseFromUrl(url, params).getResult();
        return responseResult.getItems().get(0);
    }

    @Override
    public List<Item> searchItems(Map<String, Object> params) {
        String url = "https://catalog.api.2gis.com/3.0/items";
        DoubleGisResponse doubleGisResponse = getDoubleGisResponseFromUrl(url, params);
        Result responseResult = doubleGisResponse.getResult();
        System.out.println(responseResult);

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