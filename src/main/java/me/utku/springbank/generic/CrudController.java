package me.utku.springbank.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Generic CRUD controller for all entities.
 *
 * @param <Entity>
 * @apiNote This class is abstract and should be extended by a concrete controller.
 */
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public abstract class CrudController<Entity> {
    private final CrudService<Entity> crudService;

    @GetMapping
    public List<BaseDto<Entity>> getAll() {
        return crudService.getEntities();
    }

    @GetMapping("/{id}")
    public BaseDto<Entity> getEntity(@PathVariable UUID id) {
        return crudService.getEntity(id);
    }

    @PostMapping
    public ResponseEntity<GenericResponse<BaseDto<Entity>>> createEntity(BaseDto<Entity> dto) {
        return crudService.createEntity(dto).toResponseEntity();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenericResponse<BaseDto<Entity>>> updateEntity(@PathVariable UUID id, BaseDto<Entity> dto) {
        return crudService.updateEntity(id, dto).toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Boolean>> deleteEntity(@PathVariable UUID id) {
        return crudService.deleteEntity(id).toResponseEntity();
    }
}
