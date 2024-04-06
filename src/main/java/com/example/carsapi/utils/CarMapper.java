package com.example.carsapi.utils;

import com.example.carsapi.dto.CarDto;
import com.example.carsapi.model.CarModel;

public class CarMapper {
    public static CarModel toCarModel(CarDto carDto){
        CarModel carModel = new CarModel();
        carModel.setBrand(carDto.getBrand());
        carModel.setModel(carDto.getModel());
        carModel.setColor(carDto.getColor());
        return carModel;
    }
    public static CarDto toCarDto(CarModel carModel){
        CarDto carDto = new CarDto();
        carDto.setBrand(carModel.getBrand());
        carDto.setBrand(carModel.getModel());
        carDto.setColor(carModel.getColor());
        return carDto;
    }
}
