package me.utku.springbank.service.user;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.generic.CrudService;
import me.utku.springbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCrudService extends CrudService<User> {
    public UserCrudService(JpaRepository<User, UUID> repository, BaseMapper<User> mapper) {
        super(repository, mapper);
    }
}
