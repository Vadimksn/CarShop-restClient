package com.vadimksn.shop.cars.domain.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class EntityFactory {
    private static EntityManagerFactory instance;

    private EntityFactory() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (instance == null)
            instance = Persistence.createEntityManagerFactory("hibernate-unit");
        return instance;
    }
}
