package com.boldyrev.sensorrestreader.dto;

import com.boldyrev.sensorrestreader.enums.SensorType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {

    @NotNull(message = "Name can't be null")
    @Pattern(message = "Name should contains only [a-zA-Z0-9_], more than 4 chars", regexp = "\\w{4,}")
    private String name;

    @NotNull(message = "Type can't be null")
    private SensorType type;
}
