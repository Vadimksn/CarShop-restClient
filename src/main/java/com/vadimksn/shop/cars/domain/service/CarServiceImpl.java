package com.vadimksn.shop.cars.domain.service;



import com.vadimksn.shop.cars.data.dao.Dao;
import com.vadimksn.shop.cars.data.entity.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService<CarEntity> {
    private final Dao<CarEntity> dao;

    @Autowired
    public CarServiceImpl(Dao<CarEntity> dao) {
        this.dao = dao;
    }

    @Override
    public CarEntity addCar(CarEntity carEntity) {
        return dao.add(carEntity);
    }

    @Override
    public void deleteCar(CarEntity carEntity) {
        dao.delete(carEntity);
    }

    @Override
    public CarEntity updateCar(CarEntity carEntity) {
        return dao.update(carEntity);
    }

    @Override
    public CarEntity findById(Object id) {
        return dao.findById(id);
    }

    @Override
    public List<CarEntity> getAllCars() {
        return dao.getAll();
    }
}
