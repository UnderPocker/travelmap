package com.akr.course.travelmap.dto_entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность расстояния между двумя точками")
public class Distance {
    @Schema(description = "Расстояние пути в метрах", example = "1200")
    private Integer distance;
    @Schema(description = "Путь в минутах пешком", example = "16")
    private Integer duration;
    @NotNull
    @Schema(description = "Удалось ли построить маршрут между двумя точками", example = "true")
    private Boolean isAvailable;
}
