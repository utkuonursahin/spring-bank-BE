package me.utku.springbank.service.card;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.generic.CrudService;
import me.utku.springbank.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardCrudService extends CrudService<Card> {
    public CardCrudService(JpaRepository<Card, UUID> repository, BaseMapper<Card> mapper) {
        super(repository, mapper);
    }
}
