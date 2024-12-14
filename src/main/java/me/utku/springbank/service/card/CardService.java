package me.utku.springbank.service.card;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.card.CardDto;
import me.utku.springbank.dto.card.UpdateCardPinRequest;
import me.utku.springbank.dto.card.UpdateCardSettingsRequest;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.exception.OperationDeniedException;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.CardMapper;
import me.utku.springbank.model.Card;
import me.utku.springbank.model.User;
import me.utku.springbank.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public GenericResponse<CardDto> createCard(User user, CardDto cardDto) {
        Card card = cardMapper.toEntity(cardDto);
        card.setOwner(user);
        cardRepository.save(card);
        return GenericResponse.ok(HttpStatus.CREATED.value(), cardMapper.toDto(card));
    }

    public GenericResponse<CardDto> updateCardSettings(UUID userId, UpdateCardSettingsRequest updateCardSettingsRequest) {
        Card card = cardRepository.findByOwner_Id(userId).orElseThrow(EntityNotFoundException::new);
        card = cardMapper.updateEntity(card, updateCardSettingsRequest);
        cardRepository.save(card);
        return GenericResponse.ok(HttpStatus.OK.value(), cardMapper.toDto(card));
    }

    public GenericResponse<CardDto> updateCardPin(UUID userId, UpdateCardPinRequest updateCardPinRequest) {
        Card card = cardRepository.findByOwner_Id(userId).orElseThrow(EntityNotFoundException::new);
        if (card.getPin() != updateCardPinRequest.oldPin()) {
            throw new OperationDeniedException("Old pin is incorrect");
        }
        card.setPin(updateCardPinRequest.newPin());
        cardRepository.save(card);
        return GenericResponse.ok(HttpStatus.OK.value(), cardMapper.toDto(card));
    }
}
