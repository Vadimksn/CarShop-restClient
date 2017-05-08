package com.vadimksn.shop.cars.data.dao;

import java.util.List;

public interface Dao<T> {
    T add(T t);

    void delete(T t);

    T update(T t);

    T findById(Object id);

    List<T> getAll();

}
