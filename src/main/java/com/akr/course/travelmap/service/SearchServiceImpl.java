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
    private static Map<Integer, Set<Integer>> types;

    static {
        types = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            types.put(i, new HashSet<>());
        }

        Set<Integer> entertainment = types.get(0);
        entertainment.addAll(Arrays.asList(192, 170, 173, 169, 21387, 6505, 51221, 51008, 685, 112872, 159,
                167, 9508, 72370, 110345, 537, 110427, 110358, 11974, 111526, 24353));

        Set<Integer> catering = types.get(1);
        catering.addAll(Arrays.asList(161, 164, 159, 162, 166, 165, 15791, 51459, 112658, 52248, 363, 111594));

        Set<Integer> culture = types.get(2);
        culture.addAll(Arrays.asList(193, 168, 13787, 112670, 112668, 112720, 13374, 942, 199, 190));
    }
    /*static {
        types = new HashMap<>();

        //Досуг:
        types.put(192, "Кинотеатры");
        types.put(170, "Боулинг");
        types.put(173, "Ночные клубы");
        types.put(169, "Бильярдные залы");
        types.put(21387, "Караоке-залы");
        types.put(6505, "Стрелковые клубы");
        types.put(51221, "Гольф-клубы");
        types.put(51008, "Картинг");
        types.put(685, "Цирк");
        types.put(112872, "Уличные катки");
        types.put(159, "Бары");
        types.put(167, "Зоопарк");
        types.put(9508, "Дельфинарий");
        types.put(72370, "Антикафе");
        types.put(110345, "Научно-развлекательные центры");
        types.put(537, "Аквапарки / Водные аттракционы");
        types.put(110427, "Батутные центры");
        types.put(110358, "Аттракционы");
        types.put(11974, "Ледовые дворцы / Катки");
        types.put(111526, "Смотровые площадки");
        types.put(24353, "Пляжи / Зоны пляжного отдыха");

        //Поесть:
        types.put(161, "Кафе");
        types.put(164, "Рестораны");
        types.put(159, "Бары");
        types.put(162, "Кофейни");
        types.put(166, "Столовые");
        types.put(165, "Быстрое питание");
        types.put(15791, "Суши-бары");
        types.put(51459, "Пиццерии");
        types.put(112658, "Кафе-кондитерские");
        types.put(52248, "Рюмочные");
        types.put(363, "Кондитерские изделия");
        types.put(111594, "Пекарни");

        //Культура:
        types.put(193, "Музеи");
        types.put(168, "Парки культуры и отдыха");
        types.put(13787, "Дома / дворцы культуры");
        types.put(112670, "Интересные здания");
        types.put(112668, "Природные достопримечательности");
        types.put(112720, "Фонтаны");
        types.put(13374, "Мечети");
        types.put(942, "Организация выставок");
        types.put(199, "Театры");
        types.put(190, "Художественные выставки / Галереи");
    }*/
    @Override
    public List<Place> searchPlaces(SearchFilters searchFilters) {
        Map<String, Object> urlParams = new HashMap<>();

        urlParams.put("region_id", "19");
        //urlParams.put("city_id", "2674635049009413");
        urlParams.put("key", apiKey);
        if (searchFilters.getTypes() != null && !searchFilters.getTypes().isEmpty())
            urlParams.put("rubric_id", StringUtils.collectionToDelimitedString(searchFilters.getTypes(), ","));

        String[] fields = new String[]{"items.point", "items.external_content", "items.rubrics", "items.schedule", "items.reviews", "items.ads.options", "items.context"};
        urlParams.put("fields", StringUtils.collectionToDelimitedString(Arrays.asList(fields), ","));

        if (searchFilters.getWord() != null)
            urlParams.put("q", searchFilters.getWord());

        if (searchFilters.getLon() != null && searchFilters.getLat() != null) {
            urlParams.put("lon", searchFilters.getLon());
            urlParams.put("lat", searchFilters.getLat());
            if (searchFilters.getMaxDistance() == null)
                urlParams.put("radius", 15000);
            else
                urlParams.put("radius", searchFilters.getMaxDistance());
        }

        if (searchFilters.getPage() != null){
            urlParams.put("page", searchFilters.getPage());
        }

        if (searchFilters.getPriceRange() != null && searchFilters.getPriceRange() > 0){
            int minPrice = 0, maxPrice = 4500;
            switch (searchFilters.getPriceRange()){
                case 1:
                    maxPrice = 599;
                    break;
                case 2:
                    minPrice = 600; maxPrice = 1499;
                    break;
                case 3:
                    minPrice = 1500; maxPrice = 2499;
                    break;
                case 4:
                    minPrice = 2500;
                    break;
            }
            urlParams.put("attr[food_service_avg_price]", minPrice + "," + maxPrice);

        }

        if (searchFilters.getMinRating() != null){
            String ratingFilter = getRatingTag(searchFilters);
            urlParams.put("attr[" + ratingFilter + "]", "true");
        }

        if (searchFilters.getSort() != null){
            urlParams.put("sort", searchFilters.getSort());
        }

        if (searchFilters.getIsWorkingNow() != null && searchFilters.getIsWorkingNow())
            urlParams.put("work_time", "now");

        List<Item> items = searchItems(URL, urlParams);
        return convertItemsToPlacesDto(items);
    }

    @Override
    public List<Place> searchPlacesById(List<String> ids) {
        List<Place> places = new ArrayList<>();

        List<String> uniqueIds = new ArrayList<>(new HashSet<>(ids));
        List<Place> uniquePlaces = convertItemsToPlacesDto(searchItemsById(uniqueIds));

        for (String id : ids) {
            Place place = uniquePlaces.stream().filter(
                    uniquePlace -> uniquePlace.getId().equals(id)).findFirst().orElse(null);
            places.add(place);
        }

        return places;
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
        place.setType(item.getRubrics() != null ? item.getRubrics()[0].getName() : null);
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

        String typeStr = item.getRubrics() != null ? item.getRubrics()[0].getId() : null;
        if (typeStr != null) {
            Integer type = Integer.parseInt(typeStr);

            for (Map.Entry<Integer, Set<Integer>> pair : types.entrySet()) {
                Set<Integer> set = pair.getValue();
                if (set.contains(type)) {
                    place.setGeneralType(pair.getKey());
                    break;
                }
            }
        }

        return place;
    }

    @Override
    public List<Item> searchItemsById(List<String> ids) {
        String url = URL + "/byid";

        Map<String, Object> params = new HashMap<>();
        params.put("id", StringUtils.collectionToDelimitedString(ids, ","));
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