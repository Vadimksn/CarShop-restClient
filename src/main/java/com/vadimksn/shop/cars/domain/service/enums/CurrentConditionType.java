package com.vadimksn.shop.cars.domain.service.enums;

public enum CurrentConditionType {
    NEW("new"), USED("used"), LIKENEW("likeNew");

    public String condition;

    CurrentConditionType(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
