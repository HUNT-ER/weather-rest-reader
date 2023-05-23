package com.boldyrev.sensorrestreader.models;

import com.boldyrev.sensorrestreader.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Measurement {

    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int measurementId;

    @NonNull
    @Min(value = -100, message = "Value should not be less -100")
    @Max(value = 100, message = "Value should not be greater 100")
    @Column(name = "value")
    private Double value;

    @NonNull
    @NotNull(message = "Rain flag should can't be null")
    @Column(name = "raining")
    private Boolean isRaining;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    private Sensor sensor;

}
