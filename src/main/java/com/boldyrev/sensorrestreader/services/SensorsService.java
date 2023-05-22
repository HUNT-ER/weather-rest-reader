package com.boldyrev.sensorrestreader.services;

import com.boldyrev.sensorrestreader.models.Sensor;
import com.boldyrev.sensorrestreader.repositories.SensorsRepository;
import com.boldyrev.sensorrestreader.util.exceptions.SensorNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional(readOnly = true)
    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sensor findById(int id) {
        Optional<Sensor> sensor = sensorsRepository.findById(id);

        if (sensor.isEmpty()) {
            throw new SensorNotFoundException();
        }

        return sensor.get();
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findByName(String name) {
        return sensorsRepository.findByNameIgnoreCase(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        setCreationTimestamp(sensor);
        sensorsRepository.save(sensor);
    }

    @Transactional
    public void update(int id, Sensor sensor) {
        sensor.setSensorId(id);
        sensorsRepository.save(sensor);
    }

    @Transactional
    public void deleteById(int id) {
        sensorsRepository.deleteById(id);
    }

    private void setCreationTimestamp(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
    }
}
