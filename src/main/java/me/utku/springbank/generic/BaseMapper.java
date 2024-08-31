package me.utku.springbank.generic;

public interface BaseMapper<Entity> {
    BaseDto<Entity> toDto(Entity entity);

    Entity toEntity(BaseDto<Entity> dto);

    Entity updateEntity(Entity oldEntity, Entity newEntity);
}
