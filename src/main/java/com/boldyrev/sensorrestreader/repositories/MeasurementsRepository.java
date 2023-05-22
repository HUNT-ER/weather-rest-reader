package com.boldyrev.sensorrestreader.repositories;

import com.boldyrev.sensorrestreader.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {

}
