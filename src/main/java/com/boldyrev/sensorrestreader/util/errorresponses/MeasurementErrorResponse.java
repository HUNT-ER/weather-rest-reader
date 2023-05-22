package com.boldyrev.sensorrestreader.util.errorresponses;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasurementErrorResponse {

    private String message;
    private LocalDateTime timestamp;
}
