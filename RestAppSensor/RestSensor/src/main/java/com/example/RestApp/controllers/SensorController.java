package com.example.RestApp.controllers;

import com.example.RestApp.dto.SensorDTO;
import com.example.RestApp.models.Sensor;
import com.example.RestApp.services.SensorService;
//import com.example.RestApp.util.sensor.SensorValidator;
import com.example.RestApp.util.sensor.SensorErrorResponse;
import com.example.RestApp.util.sensor.SensorNotCreatedException;
import com.example.RestApp.util.sensor.SensorNotFoundException;
import com.example.RestApp.util.sensor.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // Controller + ResponseBody
@RequestMapping("/sensor")
public class SensorController {

    private final SensorValidator sensorValidator;
    private final SensorService sensorService;
    private final  ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorValidator sensorValidator
            , SensorService sensorService,
                            ModelMapper modelMapper) {
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public List<Sensor> sensors(){ //Возвращает все сенсоры
        return sensorService.findAll();
    }

    @GetMapping("/{name}") //Возвращает сенсор по имени
    public Sensor sensor(@PathVariable("name") String name){
        return sensorService.findBySensorName(name);
    }



    @PostMapping("/registration") //добавляет новый сенсор в бд
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO,
             BindingResult bindingResult){ // Добавление нового сенсора в БД

        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult); // выдает ошибку если названия сенсоров одинаковые

        if(bindingResult.hasErrors()){
            StringBuilder errorMassage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMassage.append(error.getField()) // поле на котором совершена ошибка
                        .append(" - ").append(error.getDefaultMessage()) // выдаст ошибку на этом поле
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMassage.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler // ловит определенное исключение (помечено классом исключения
    // если не указать класс исключения ловил бы все искл
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e){
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor with this name wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404
    }

    @ExceptionHandler // ловит определенное исключение (помечено классом исключения
    // если не указать класс исключения ловил бы все искл
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
