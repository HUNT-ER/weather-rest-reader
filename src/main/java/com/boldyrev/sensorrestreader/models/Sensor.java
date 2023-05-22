package com.boldyrev.sensorrestreader.models;

import com.boldyrev.sensorrestreader.enums.SensorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "sensors")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Sensor {

    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sensorId;

    @NonNull
    @Column(name = "name")
    @Pattern(message = "Name should contains only [a-zA-Z0-9_], more than 4 chars", regexp = "\\w{4,}")
    private String name;

    @NonNull
    @Column(name = "type")
    private SensorType type;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;
}
