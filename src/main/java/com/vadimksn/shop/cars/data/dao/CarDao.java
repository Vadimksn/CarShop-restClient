package com.vadimksn.shop.cars.data.dao;


import com.vadimksn.shop.cars.data.entity.CarEntity;
import com.vadimksn.shop.cars.domain.service.EntityManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDao implements Dao<CarEntity> {
    private final EntityManagerHelper<CarEntity> entityManagerHelper;

    @Autowired
    public CarDao(EntityManagerHelper<CarEntity> entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public CarEntity add(CarEntity carEntity) {
        return entityManagerHelper.transaction(carEntity, EntityManagerHelper.OperationType.ADD);
    }

    @Override
    public void delete(CarEntity carEntity) {
        entityManagerHelper.transaction(carEntity, EntityManagerHelper.OperationType.DELETE);
    }

    @Override
    public CarEntity update(CarEntity carEntity) {
        return entityManagerHelper.transaction(carEntity, EntityManagerHelper.OperationType.UPDATE);
    }

    @Override
    public CarEntity findById(Object id) {
        EntityManager manager = entityManagerHelper.getEntityManager();
        CarEntity carEntity = manager.find(CarEntity.class, id);
        manager.close();
        return carEntity;
    }

    @Override
    public List<CarEntity> getAll() {
        EntityManager manager = entityManagerHelper.getEntityManager();
        TypedQuery<CarEntity> typedQuery = manager.createQuery("SELECT c FROM CarEntity c", CarEntity.class);
        List<CarEntity> carEntities = typedQuery.getResultList();
        manager.close();
        return carEntities;
    }

}
