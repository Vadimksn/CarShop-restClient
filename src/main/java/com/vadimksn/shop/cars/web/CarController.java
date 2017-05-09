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

import java.util.Arrays;
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
            logger.error("Database is empty.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("All entities were loaded from database : " + Arrays.toString(carDtos.toArray()));
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/id{id}", method = RequestMethod.GET)
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") int id) {
        CarEntity carEntity = carService.findById(id);
        if (carEntity == null) {
            logger.error("Entity by id:[" + id + "] was not found in database.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CarDto carDto = carMapper.convertToDto(carEntity);
        logger.info("Entity [" + carDto + " ] by id:[" + id + "] was loaded from database.");
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        CarEntity carEntity = carMapper.convertToEntity(carDto);
        if (carEntity.validateToPost()) {
            CarDto addedCarDto = carMapper.convertToDto(carService.addCar(carEntity));
            logger.info("Entity [" + addedCarDto + "] was added in database.");
            return new ResponseEntity<>(addedCarDto, HttpStatus.OK);
        }
        logger.error("Entity: [" + carEntity + "] is not valid.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) {
        CarEntity carEntity = carMapper.convertToEntity(carDto);
        if (carEntity.getId() == 0||!carEntity.validateToPut()) {
            logger.error("Dto: [" + carEntity + "] is not valid.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CarDto updatedCarDto = carMapper.convertToDto(carService.updateCar(carMapper.convertToEntity(carDto)));
        logger.info("Entity: [" + updatedCarDto + "] was updated.");
        return new ResponseEntity<>(updatedCarDto, HttpStatus.OK);
    }


    @RequestMapping(value = "/id{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCarById(@PathVariable("id") int id) {
        CarEntity carEntity = carService.findById(id);
        if (carEntity == null) {
            logger.error("Entity to delete by id:[" + id + "] was not found in database.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (carService.deleteCar(carEntity)) {
            logger.info("Entity:[" + carEntity + "] was deleted by id:[" + id + "] from database.");
            return new ResponseEntity(HttpStatus.OK);
        } else logger.error("Error while deleting entity:[" + carEntity + "] by id:[" + id + "].");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
