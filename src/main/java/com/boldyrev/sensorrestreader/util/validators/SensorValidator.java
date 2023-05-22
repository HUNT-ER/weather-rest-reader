package com.boldyrev.sensorrestreader.util.validators;

import com.boldyrev.sensorrestreader.dto.SensorDTO;
import com.boldyrev.sensorrestreader.models.Sensor;
import com.boldyrev.sensorrestreader.services.SensorsService;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        Optional<Sensor> storedSensor = sensorsService.findByName(sensorDTO.getName());

        if (storedSensor.isPresent()) {
            errors.rejectValue("name", "name_error", "Sensor already registered");
        }
    }
}
