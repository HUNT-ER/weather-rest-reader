package com.boldyrev.sensorrestreader.util.validators;

import com.boldyrev.sensorrestreader.dto.MeasurementDTO;
import com.boldyrev.sensorrestreader.dto.SensorDTO;
import com.boldyrev.sensorrestreader.models.Sensor;
import com.boldyrev.sensorrestreader.services.SensorsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        SensorDTO sensorDTO = measurementDTO.getSensor();

        if (sensorDTO != null) {
            Optional<Sensor> optionalSensor = sensorsService.findByName(sensorDTO.getName());

            if (optionalSensor.isEmpty()) {
                errors.rejectValue("sensor", "sensor_error",
                    "Sensor not registered");
            }
        }
    }
}
