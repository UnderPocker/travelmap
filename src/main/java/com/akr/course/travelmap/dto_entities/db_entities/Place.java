package com.akr.course.travelmap.dto_entities.db_entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность места")

@Entity
@Table(name = "place")
public class Place {
    @NotNull
    @Schema(example = "70000001039646535")
    @Id
    @Column(nullable = false)
    private String id;

    @NotNull
    @Schema(example = "44.005713")
    @Column(nullable = false)
    private Double lon;

    @NotNull
    @Schema(example = "56.32716")
    @Column(nullable = false)
    private Double lat;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="photo",
            joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "photo_url")
    @OrderColumn(name = "`index`")
    private List<String> photos;

    @NotNull
    @Schema(example = "Нижегородский кремль, музей-заповедник")
    @Column(nullable = false)
    private String name;

    @Schema(example = "193")
    @Column
    private String type;

    @Schema(example = "Кремль, 6а")
    @Column
    private String address;

    @NotNull
    @Schema(description = "Ссылка на объект на карте 2гиса", example = "https://2gis.ru/n_novgorod/firm/70000001039646535")
    @Column(nullable = false, name = "double_gis_link")
    private String doubleGisLink;

    @Schema(example = "10:00 - 19:00")
    @Column(name = "work_time")
    private String workTime;

    @Column
    private String cost;

    @Schema(example = "4.8")
    @Column
    private Double rating;

    @Schema(example = "916")
    @Column
    private Integer reviews;

    @Schema(description = "Ссылка на сайт заведения")
    @Column
    private String link;

    @Column
    private String phone;
}