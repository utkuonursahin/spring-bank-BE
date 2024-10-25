package me.utku.springbank.generic;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

/**
 * Generic service for basic CRUD operations.
 *
 * @param <Entity>
 * @apiNote This class is abstract and should be extended by a crud service per domain.
 */
@RequiredArgsConstructor
public abstract class CrudService<Entity> {
    protected final JpaRepository<Entity, UUID> repository;
    protected final BaseMapper<Entity> mapper;

    public List<BaseDto<Entity>> getEntities() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public BaseDto<Entity> getEntity(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

    public GenericResponse<BaseDto<Entity>> createEntity(BaseDto<Entity> dto) {
        Entity savedEntity = repository.save(mapper.toEntity(dto));
        return GenericResponse.ok(HttpStatus.CREATED.value(), mapper.toDto(savedEntity));
    }

    public GenericResponse<BaseDto<Entity>> updateEntity(UUID id, BaseDto<Entity> newEntity) {
        Entity oldEntity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        Entity updatedEntity = mapper.updateEntity(oldEntity, mapper.toEntity(newEntity));
        updatedEntity = repository.save(updatedEntity);
        return GenericResponse.ok(mapper.toDto(updatedEntity));
    }

    public GenericResponse<Boolean> deleteEntity(UUID id) {
        Entity entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(entity);
        return GenericResponse.ok(true);
    }
}
