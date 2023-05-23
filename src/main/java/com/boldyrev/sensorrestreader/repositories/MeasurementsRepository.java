package com.boldyrev.sensorrestreader.repositories;

import com.boldyrev.sensorrestreader.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {

    //@Query("select count(m) from Measurement m WHERE m.isRaining = ?1")
    Long countAllByIsRaining(boolean isRaining);
}
