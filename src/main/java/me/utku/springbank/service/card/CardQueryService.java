package me.utku.springbank.service.card;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.card.CardDto;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.mapper.CardMapper;
import me.utku.springbank.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardQueryService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardDto getUserCard(UUID userId) {
        return cardMapper.toDto(cardRepository.findByOwner_Id(userId).orElseThrow(EntityNotFoundException::new));
    }
}
