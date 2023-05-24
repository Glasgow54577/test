package com.example.RestApp.services;


import com.example.RestApp.models.Measurements;
import com.example.RestApp.models.Sensor;
import com.example.RestApp.repositories.MeasurementsRepository;
import com.example.RestApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true )
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService, SensorRepository sensorRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurements> findAll(){
        return measurementsRepository.findAll();
    }

    public List<Measurements> findRainingDayCount(){
        return measurementsRepository.findByRaining(true);
    }

 public Optional<Sensor> findBySensor(Measurements measurements){
        return sensorRepository.findByName(measurements.getSensor().getName());
    }

    @Transactional
    public void save(Measurements measurements){
        enrichMeasurement(measurements);
        measurementsRepository.save(measurements);
    }

    private void enrichMeasurement(Measurements measurements) {
        measurements.setSensor(sensorService.findBySensorName(measurements.getSensor().getName()));
        measurements.setCurrentTime(LocalDateTime.now());
    }

}
