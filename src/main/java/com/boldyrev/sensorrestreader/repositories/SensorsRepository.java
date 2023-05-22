package com.boldyrev.sensorrestreader.repositories;

import com.boldyrev.sensorrestreader.models.Sensor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findByNameIgnoreCase(String name);
}
