package com.example.carsapi.service;

import com.example.carsapi.dto.CarDto;
import com.example.carsapi.model.CarModel;
import com.example.carsapi.repository.CarRepository;
import com.example.carsapi.utils.CarMapper;
import com.example.carsapi.utils.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarModel> getCarList() {
    return carRepository.findAll();
    }
    public CarModel getCarById(Long id) {
         return carRepository.findById(id).orElseThrow(()->new RuntimeException("cc"));
    }
    public List<CarModel> findCarByColor(String color) {
        return carRepository.findCarsByColor(color);
    }

    public CarDto addCar(CarDto model) {
        CarModel carModel = CarMapper.toCarModel(model);
        CarModel newCar = carRepository.save(carModel);
        return CarMapper.toCarDto(newCar);
    }

    public void editCarById(CarModel carModel, Long id) {
        carModel.setId(id);
        carRepository.save(carModel);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }


    public CarDto updateColor(Long id, CarDto carDto) {
        CarModel changeColorById = carRepository.findById(id)
                    .orElseThrow(()-> new CarNotFoundException("Car doesn't exist"));
            changeColorById.setColor(carDto.getColor());
            CarModel saved = carRepository.save(changeColorById);
            return CarMapper.toCarDto(saved);
    }
}
