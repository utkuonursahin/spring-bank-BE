package me.utku.springbank.generic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public abstract class CrudController<Entity> {
    private final CrudService<Entity> crudService;

    protected CrudController(CrudService<Entity> crudService) {
        this.crudService = crudService;
    }
    
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
}
