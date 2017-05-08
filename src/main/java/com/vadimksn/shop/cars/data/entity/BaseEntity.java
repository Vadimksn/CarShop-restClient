package com.vadimksn.shop.cars.data.entity;

import javax.persistence.*;

@MappedSuperclass
abstract class BaseEntity {
    protected int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
