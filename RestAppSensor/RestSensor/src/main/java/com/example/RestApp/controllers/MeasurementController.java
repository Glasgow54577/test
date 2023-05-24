package com.example.RestApp.controllers;

import com.example.RestApp.dto.MeasurementsDTO;
import com.example.RestApp.models.Measurements;
import com.example.RestApp.models.Sensor;
import com.example.RestApp.services.MeasurementsService;
import com.example.RestApp.util.measuremetns.MeasurementErrorResponse;
import com.example.RestApp.util.measuremetns.MeasurementNotCreatedException;
import com.example.RestApp.util.measuremetns.MeasurementValidator;
import com.example.RestApp.util.sensor.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RestController // Controller + ResponseBody
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementsService measurementsService;
    private final  ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;


    @Autowired
    public MeasurementController(MeasurementsService measurementsService,
                                 ModelMapper modelMapper,
                                 MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public List<Measurements> measurements(){ //Возвращает все измерения
        return measurementsService.findAll();
    }

    @GetMapping("/RainyDaysCount") // возвращает количество дождливых дней
    public int CountRainingDay(){
        List<Measurements> RainingDays = measurementsService.findRainingDayCount();
        return RainingDays.size();
    }



    @PostMapping("/add") //Принмиает измерения от сенсора
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                   BindingResult bindingResult){
        measurementValidator.validate(convertToMeasurement(measurementsDTO), bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMassage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMassage.append(error.getField()) // поле на котором совершена ошибка
                        .append(" - ").append(error.getDefaultMessage()) // выдаст ошибку на этом поле
                        .append(";");
            }
            throw new MeasurementNotCreatedException(errorMassage.toString());
        }

        measurementsService.save(convertToMeasurement(measurementsDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler // Обрабатывает исключение при добавлении измерений с несущ. сенсора
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurements convertToMeasurement(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }



}
