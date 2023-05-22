package com.boldyrev.sensorrestreader.dto;

import com.boldyrev.sensorrestreader.enums.SensorType;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorDTO {

    @Pattern(message = "Name should contains only [a-zA-Z0-9_], more than 4 chars", regexp = "\\w{4,}")
    private String name;

    private SensorType type;
}
