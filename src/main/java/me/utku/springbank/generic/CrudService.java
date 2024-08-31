package me.utku.springbank.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class CrudService<Entity> {
    protected final JpaRepository<Entity, UUID> repository;
    protected final BaseMapper<Entity> mapper;

    public List<BaseDto<Entity>> getEntities() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public BaseDto<Entity> getEntity(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    public GenericResponse<BaseDto<Entity>> createEntity(BaseDto<Entity> dto) {
        Entity savedEntity = repository.save(mapper.toEntity(dto));
        return this.generateCreatedResponse(mapper.toDto(savedEntity));
    }

    public GenericResponse<BaseDto<Entity>> updateEntity(UUID id, BaseDto<Entity> newEntity) {
        Entity existingEntity = repository.findById(id).orElseThrow();
        Entity updatedEntity = mapper.updateEntity(mapper.toEntity(newEntity), existingEntity);
        updatedEntity = repository.save(updatedEntity);
        return generateUpdateResponse(mapper.toDto(updatedEntity));
    }

    public GenericResponse<Boolean> deleteEntity(UUID id) {
        Entity entity = repository.findById(id).orElseThrow();
        repository.delete(entity);
        return generateDeleteResponse();
    }

    private GenericResponse<BaseDto<Entity>> generateCreatedResponse(BaseDto<Entity> dto) {
        return GenericResponse.<BaseDto<Entity>>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Entity created successfully")
                .data(dto).build();
    }

    private GenericResponse<BaseDto<Entity>> generateUpdateResponse(BaseDto<Entity> dto) {
        return GenericResponse.<BaseDto<Entity>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Entity updated successfully")
                .data(dto).build();
    }

    private GenericResponse<Boolean> generateDeleteResponse() {
        return GenericResponse.<Boolean>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Entity deleted successfully")
                .data(true).build();
    }
}
