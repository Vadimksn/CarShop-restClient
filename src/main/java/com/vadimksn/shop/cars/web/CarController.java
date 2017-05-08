package com.vadimksn.shop.cars.web;


import com.vadimksn.shop.cars.data.dto.CarDto;
import com.vadimksn.shop.cars.data.entity.CarEntity;
import com.vadimksn.shop.cars.domain.mapper.CarMapper;
import com.vadimksn.shop.cars.domain.service.CarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final static Logger logger = Logger.getLogger(CarController.class);

    private final CarService<CarEntity> carService;

    private final CarMapper carMapper;

    @Autowired
    public CarController(CarService<CarEntity> carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtos = carMapper.convertToDto(carService.getAllCars());
        if (carDtos.isEmpty()) {
            logger.info("CarController.getAllCars(), HttpStatus.NO_CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") int id) {
        CarDto carDto = carMapper.convertToDto(carService.findById(id));
        if (carDto == null) {
            logger.info("CarController.getCarById(), HttpStatus.NO_CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        CarDto addedCarDto = carMapper.convertToDto(carService.addCar(carMapper.convertToEntity(carDto)));
        if (addedCarDto != null) {
            return new ResponseEntity<>(addedCarDto, HttpStatus.OK);
        } else logger.info("CarController.addCar(), HttpStatus.NO_CONTENT");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) {
        CarDto updatedCarDto = carMapper.convertToDto(carService.updateCar(carMapper.convertToEntity(carDto)));
        if (updatedCarDto.getId() == carDto.getId()) {
            return new ResponseEntity<>(updatedCarDto, HttpStatus.OK);
        } else logger.info("CarController.updateCar(), HttpStatus.BAD_REQUEST");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCarById(@PathVariable("id") int id) {
        CarEntity carEntity = carService.findById(id);
        carService.deleteCar(carEntity);
        if (carService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.OK);
        } else logger.info("CarController.deleteCarById(), HttpStatus.BAD_REQUEST");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
