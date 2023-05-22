package com.boldyrev.sensorrestreader.services;

import com.boldyrev.sensorrestreader.models.Measurement;
import com.boldyrev.sensorrestreader.models.Sensor;
import com.boldyrev.sensorrestreader.repositories.MeasurementsRepository;
import com.boldyrev.sensorrestreader.util.exceptions.MeasurementNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional(readOnly = true)
    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Measurement findById(int id) {
        Optional<Measurement> measurement = measurementsRepository.findById(id);

        if (measurement.isEmpty()) {
            throw new MeasurementNotFoundException();
        }

        return measurement.get();
    }

    @Transactional
    public Measurement save(Measurement measurement) {
        measurementsRepository.save(measurement);
    }

    private void setCreationTimestamp(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
