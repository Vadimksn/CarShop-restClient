package com.vadimksn.shop.cars.data.dto;

public class CarDto {
    private int id;
    private int year;
    private int price;
    private boolean available;
    private String mark;
    private String model;
    private String color;
    private String currentCondition;

    public CarDto() {
    }

    public CarDto(int id, int year, int price, boolean available,
                  String mark, String model, String color, String currentCondition) {
        this.id = id;
        this.year = year;
        this.price = price;
        this.available = available;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.currentCondition = currentCondition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }
}
