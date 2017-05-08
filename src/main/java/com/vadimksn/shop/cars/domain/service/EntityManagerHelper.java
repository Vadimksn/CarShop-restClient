package com.vadimksn.shop.cars.domain.service;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class EntityManagerHelper<T> {
    private EntityManagerFactory managerFactory = EntityFactory.getEntityManagerFactory();
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager = managerFactory.createEntityManager();
    }

    public enum OperationType {
        ADD, DELETE, UPDATE

    }


    public T transaction(T t, OperationType operationType) {
        entityManager = getEntityManager();

        try {
            entityManager.getTransaction().begin();
            switch (operationType) {
                case ADD:
                    entityManager.persist(t);
                    break;
                case UPDATE:
                    entityManager.merge(t);
                    break;
                case DELETE:
                    T t1 = entityManager.merge(t);
                    entityManager.remove(t1);
                    break;
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return t;
    }


}
