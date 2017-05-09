package com.vadimksn.shop.cars.data.entity;

import com.vadimksn.shop.cars.domain.service.enums.CurrentConditionType;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class CarEntity extends BaseEntity {

    private int year;
    private int price;
    private boolean available;
    private String mark;
    private String model;
    private String color;
    private String currentCondition;

    public CarEntity() {
    }

    public CarEntity(String mark, String model, String color, int year, int price, CurrentConditionType currentCondition) {
        available = true;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.currentCondition = currentCondition.getCondition();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "current_condition")
    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + getId() +
                ", year=" + year +
                ", price=" + price +
                ", available=" + available +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", currentCondition='" + currentCondition + '\'' +
                '}';
    }

    public boolean validateToPost() {
        return !(getId() != 0 || year <= 0 || price <= 0 ||
                mark == null || model == null || color == null || currentCondition == null);
    }

    public boolean validateToPut() {
        return !(getId() < 1 || year <= 0 || price <= 0 ||
                mark == null || model == null || color == null || currentCondition == null);
    }
}
