package com.boldyrev.sensorrestreader.controllers;

import com.boldyrev.sensorrestreader.dto.MeasurementDTO;
import com.boldyrev.sensorrestreader.models.Measurement;
import com.boldyrev.sensorrestreader.services.MeasurementsService;
import com.boldyrev.sensorrestreader.util.errorresponses.MeasurementErrorResponse;
import com.boldyrev.sensorrestreader.util.exceptions.MeasurementValidationException;
import com.boldyrev.sensorrestreader.util.validators.MeasurementValidator;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService,
        ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @Validated
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(
        @RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            bindingResult.getFieldErrors().forEach(
                err -> builder.append(err.getField())
                    .append(": ")
                    .append(err.getDefaultMessage())
                    .append("; ")
            );
            throw new MeasurementValidationException(builder.toString());
        }

        measurementsService.save(convertDTOToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        List<MeasurementDTO> measurements = measurementsService.findAll().stream()
            .map(m -> modelMapper.map(m, MeasurementDTO.class)).collect(
                Collectors.toList());
        return measurements;
    }

    @GetMapping("/rainy_days_count")
    public Map<String, Long> getRainyDaysCount(
        @RequestParam(value = "raining", defaultValue = "true") boolean isRaining) {

        Map<String, Long> response = new HashMap<>();
        response.put("count", measurementsService.countAllByIsRaining(isRaining));
        return response;
    }

    private Measurement convertDTOToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertMeasurementToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(
        MeasurementValidationException e) {
        return new ResponseEntity<>(
            new MeasurementErrorResponse(e.getMessage(), LocalDateTime.now()),
            HttpStatus.BAD_REQUEST);
    }

}


