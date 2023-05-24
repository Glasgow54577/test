package com.example.RestApp.dto;


import com.example.RestApp.models.Sensor;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MeasurementsDTO {


    @Min(value = -100, message = "Value should be greater than 0")
    @Max(value = 100, message = "Value should be more than 0")
    private int value;

    private boolean raining;

    @NotNull
    private Sensor sensor;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
