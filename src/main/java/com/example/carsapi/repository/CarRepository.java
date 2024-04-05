package com.example.carsapi.repository;

import com.example.carsapi.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findCarsByColor(String color);
    @Query("SELECT c FROM CarModel c WHERE c.color = :color")
    List<CarModel> findCarModelByColor(@Param("color") String color);
}
