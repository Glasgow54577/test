package com.example.RestApp.repositories;

import com.example.RestApp.models.Measurements;
import com.example.RestApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
    List<Measurements> findByRaining (boolean raining);

}
