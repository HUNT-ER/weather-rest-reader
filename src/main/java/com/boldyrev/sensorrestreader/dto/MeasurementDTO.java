package com.boldyrev.sensorrestreader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDTO {

    @NotNull(message = "value can't be null")
    @Min(value = -100, message = "Value should not be less -100")
    @Max(value = 100, message = "Value should not be greater 100")
    private Double value;

    @NotNull(message = "Rain flag should can't be null")
    @Column(name = "is_raining")
    @JsonProperty("raining")
    private Boolean isRaining;

    @Valid
    @NotNull(message = "Sensor can't be null")
    private SensorDTO sensor;
}
