package com.boldyrev.sensorrestreader.controllers;

import com.boldyrev.sensorrestreader.dto.SensorDTO;
import com.boldyrev.sensorrestreader.models.Sensor;
import com.boldyrev.sensorrestreader.repositories.SensorsRepository;
import com.boldyrev.sensorrestreader.services.SensorsService;
import com.boldyrev.sensorrestreader.util.errorresponses.SensorErrorResponse;
import com.boldyrev.sensorrestreader.util.exceptions.SensorValidationException;
import com.boldyrev.sensorrestreader.util.validators.SensorValidator;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorsService sensorsService, ModelMapper modelMapper,
        SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
        BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            bindingResult.getFieldErrors().forEach(
                err -> builder.append(err.getField())
                    .append(": ")
                    .append(err.getDefaultMessage())
                    .append("; ")
            );
            throw new SensorValidationException(builder.toString());
        }

        sensorsService.save(convertDtoToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private SensorDTO convertSensorToDto(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertDtoToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(
        SensorValidationException exception) {
        return new ResponseEntity<>(
            new SensorErrorResponse(exception.getMessage(), LocalDateTime.now()),
            HttpStatus.BAD_REQUEST);
    }

}
