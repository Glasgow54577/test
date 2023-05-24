package com.example.RestApp.services;


import com.example.RestApp.models.Sensor;
import com.example.RestApp.repositories.SensorRepository;
import com.example.RestApp.util.sensor.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true )
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findBySensorName(String name){
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);
            return foundSensor.orElseThrow(SensorNotFoundException::new);
    }


    public Optional<Sensor> findBySensorNameSave(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }


}
