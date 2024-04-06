package com.example.carsapi.controller;

import com.example.carsapi.dto.CarDto;
import com.example.carsapi.model.CarModel;
import com.example.carsapi.service.CarService;
import com.example.carsapi.utils.error.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {
    private final CarService carService;
    private final List<CarModel> cars;

    public CarController(CarService carService) {
        this.carService = carService;
        this.cars = new ArrayList<>();
        cars.add(new CarModel(1L, "Ford", "Escort", "Black"));
        cars.add(new CarModel(2L, "Mazda", "Comfort", "Yellow"));
        cars.add(new CarModel(3L, "Opel", "Astra", "Green"));
        cars.add(new CarModel(4L, "Suzuki", "X-Trail", "White"));
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCars() {
        try {
            List<CarModel> cars = carService.getCarList();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<CarModel> getCarById(@PathVariable("id") Long id) {
//        Optional<CarModel> first = cars.stream().filter(c -> c.getId() == id).findFirst();
//        return first.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
        try {
            CarModel carModel = carService.getCarById(id);
            return ResponseEntity.ok(carModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<CarModel>> getCarsByColor(@PathVariable("color") String color) {
        try {
            List<CarModel> cars = carService.findCarByColor(color);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CarDto> postAddCar(@RequestBody CarDto carDto) {
        try {
            CarDto addedCar = carService.addCar(carDto);
            return ResponseEntity.ok(addedCar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CarModel> putEditCarById(@PathVariable("id") Long id,
                                                   @RequestBody CarModel carModel) {
        carService.editCarById(carModel, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/color")
    public ResponseEntity<CarDto> updateCarColor(@PathVariable("id") Long id,
                                                 @RequestBody CarDto carDto) {
        try {
            CarDto newCarColor = carService.updateColor(id, carDto);
            return ResponseEntity.ok(newCarColor);
        } catch (CarNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<CarModel> deleteCarById(@PathVariable("id") Long id) {
        try {
            carService.deleteCarById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}