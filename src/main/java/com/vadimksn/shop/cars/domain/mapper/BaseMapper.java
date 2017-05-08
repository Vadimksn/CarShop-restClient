package com.vadimksn.shop.cars.domain.mapper;

import java.util.List;

public abstract class BaseMapper<Dto, Entity> {
    public abstract Dto convertToDto(Entity entity);
    public abstract Entity convertToEntity(Dto dto);
    public abstract List<Dto> convertToDto(List<Entity> entities);
    public abstract List<Entity> convertToEntity(List<Dto> dtos);
}
