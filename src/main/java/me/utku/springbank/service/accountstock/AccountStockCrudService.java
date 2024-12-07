package me.utku.springbank.service.accountstock;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.generic.CrudService;
import me.utku.springbank.model.AccountStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountStockCrudService extends CrudService<AccountStock> {

    public AccountStockCrudService(JpaRepository<AccountStock, UUID> repository, BaseMapper<AccountStock> mapper) {
        super(repository, mapper);
    }
}
