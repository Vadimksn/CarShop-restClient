package com.vadimksn.shop.cars.domain.service;

import java.util.List;

public interface CarService<Entity>{
    Entity addCar(Entity entity);
    boolean deleteCar(Entity entity);
    Entity updateCar(Entity entity);
    Entity findById(Object id);
    List<Entity> getAllCars();

}
