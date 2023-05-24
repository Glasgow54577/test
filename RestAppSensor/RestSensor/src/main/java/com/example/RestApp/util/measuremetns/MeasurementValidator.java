package com.example.RestApp.util.measuremetns;

import com.example.RestApp.models.Measurements;
import com.example.RestApp.models.Sensor;
import com.example.RestApp.services.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final MeasurementsService measurementsService;


    @Autowired
    public MeasurementValidator(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Measurements measurements = (Measurements) target;

        if(!measurementsService.findBySensor(measurements).isPresent()){
            errors.rejectValue("sensor", "", "This sensor does not exist");
        }
    }

}
