package me.utku.springbank.service.account;

import me.utku.springbank.model.Account;
import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.generic.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountCrudService extends CrudService<Account> {

    public AccountCrudService(JpaRepository<Account, UUID> repository, BaseMapper<Account> mapper) {
        super(repository, mapper);
    }
}
