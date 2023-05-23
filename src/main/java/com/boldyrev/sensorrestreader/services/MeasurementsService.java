package com.boldyrev.sensorrestreader.services;

import com.boldyrev.sensorrestreader.models.Measurement;
import com.boldyrev.sensorrestreader.models.Sensor;
import com.boldyrev.sensorrestreader.repositories.MeasurementsRepository;
import com.boldyrev.sensorrestreader.repositories.SensorsRepository;
import com.boldyrev.sensorrestreader.util.exceptions.MeasurementNotFoundException;
import com.boldyrev.sensorrestreader.util.exceptions.SensorNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsRepository sensorsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository,
        SensorsRepository sensorsRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsRepository = sensorsRepository;
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

    @Transactional(readOnly = true)
    public Long countAllByIsRaining(boolean isRaining) {
        return measurementsRepository.countAllByIsRaining(isRaining);
    }

    @Transactional
    public void save(Measurement measurement) {
        setCreationTimestamp(measurement);
        Optional<Sensor> sensorOptional = sensorsRepository.findByNameIgnoreCase(measurement.getSensor().getName());

        if (sensorOptional.isEmpty()) {
            throw new SensorNotFoundException();
        }

        measurement.setSensor(sensorOptional.get());
        measurementsRepository.save(measurement);
    }

    @Transactional
    public void update(int id, Measurement measurement) {
        measurement.setMeasurementId(id);
        measurementsRepository.save(measurement);
    }

    @Transactional
    public void deleteById(int id) {
        measurementsRepository.deleteById(id);
    }

    private void setCreationTimestamp(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }

}
