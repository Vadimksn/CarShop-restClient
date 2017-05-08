package com.vadimksn.shop.cars.domain.mapper;



import com.vadimksn.shop.cars.data.dto.CarDto;
import com.vadimksn.shop.cars.data.entity.CarEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarMapper extends BaseMapper<CarDto, CarEntity> {

    @Override
    public CarDto convertToDto(CarEntity carEntity) {
        CarDto carDto = new CarDto();
        carDto.setId(carEntity.getId());
        carDto.setYear(carEntity.getYear());
        carDto.setPrice(carEntity.getPrice());
        carDto.setAvailable(carEntity.isAvailable());
        carDto.setMark(carEntity.getMark());
        carDto.setModel(carEntity.getModel());
        carDto.setColor(carEntity.getColor());
        carDto.setCurrentCondition(carEntity.getCurrentCondition());
        return carDto;
    }

    @Override
    public CarEntity convertToEntity(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        if (carDto.getId() != 0) {
            carEntity.setId(carDto.getId());
        }
        carEntity.setYear(carDto.getYear());
        carEntity.setPrice(carDto.getPrice());
        carEntity.setAvailable(carDto.isAvailable());
        carEntity.setMark(carDto.getMark());
        carEntity.setModel(carDto.getModel());
        carEntity.setColor(carDto.getColor());
        carEntity.setCurrentCondition(carDto.getCurrentCondition());
        return carEntity;
    }

    @Override
    public List<CarDto> convertToDto(List<CarEntity> carEntities) {
        List<CarDto> carDtos = new ArrayList<>();
        for (CarEntity carEntity : carEntities) {
            carDtos.add(convertToDto(carEntity));
        }
        return carDtos;
    }

    @Override
    public List<CarEntity> convertToEntity(List<CarDto> carDtos) {
        List<CarEntity> carEntities = new ArrayList<>();
        for (CarDto carDto : carDtos) {
            carEntities.add(convertToEntity(carDto));
        }
        return carEntities;
    }
}
