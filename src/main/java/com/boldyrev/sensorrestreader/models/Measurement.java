package com.boldyrev.sensorrestreader.models;

import com.boldyrev.sensorrestreader.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Measurement {

    @Id
    @Column(name = "")
    private int measurementId;

    private double value;

    private boolean isRaining;

    @ManyToOne
    @JoinColumn(name = "")
    private Sensor sensor;
}
