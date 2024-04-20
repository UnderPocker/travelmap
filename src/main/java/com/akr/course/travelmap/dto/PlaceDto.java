package com.akr.course.travelmap.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность места")
public class PlaceDto {
    @NotNull
    @Schema(example = "70000001039646535")
    private String id;
    @NotNull
    @Schema(example = "44.005713")
    private String lon;
    @NotNull
    @Schema(example = "56.32716")
    private String lat;
    private List<String> photos;
    @NotNull
    @Schema(example = "Нижегородский кремль, музей-заповедник")
    private String name;
    @Schema(example = "193")
    private String type;
    @NotNull
    @Schema(example = "Кремль, 6а")
    private String address;
    @NotNull
    @Schema(description = "Ссылка на объект на карте 2гиса", example = "https://2gis.ru/n_novgorod/firm/70000001039646535")
    private String doubleGisLink;
    @Schema(example = "10:00 - 19:00")
    private String workTime;
    private String cost;
    @Schema(example = "4.8")
    private Double rating;
    @Schema(example = "916")
    private Integer reviews;
    @Schema(description = "Ссылка на сайт заведения")
    private String link;
    private String phone;
    private Boolean favorite;
}