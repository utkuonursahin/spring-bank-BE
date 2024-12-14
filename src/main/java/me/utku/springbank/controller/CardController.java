package me.utku.springbank.controller;

import me.utku.springbank.dto.card.CardDto;
import me.utku.springbank.dto.card.UpdateCardPinRequest;
import me.utku.springbank.dto.card.UpdateCardSettingsRequest;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.Card;
import me.utku.springbank.model.User;
import me.utku.springbank.service.card.CardCrudService;
import me.utku.springbank.service.card.CardQueryService;
import me.utku.springbank.service.card.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController extends CrudController<Card> {
    private final CardQueryService cardQueryService;
    private final CardService cardService;

    public CardController(CardCrudService cardCrudService, CardQueryService cardQueryService, CardService cardService) {
        super(cardCrudService);
        this.cardQueryService = cardQueryService;
        this.cardService = cardService;
    }

    @GetMapping("/me")
    public CardDto getUserCard(@AuthenticationPrincipal User user) {
        return cardQueryService.getUserCard(user.getId());
    }

    @PostMapping("/me")
    public GenericResponse<CardDto> createCard(@AuthenticationPrincipal User user, @RequestBody CardDto cardDto) {
        return cardService.createCard(user, cardDto);
    }

    @PutMapping("/me")
    public ResponseEntity<GenericResponse<CardDto>> updateCardSettings(@AuthenticationPrincipal User user, @RequestBody UpdateCardSettingsRequest updateCardSettingsRequest) {
        return cardService.updateCardSettings(user.getId(), updateCardSettingsRequest).toResponseEntity();
    }

    @PutMapping("/me/pin")
    public ResponseEntity<GenericResponse<CardDto>> updateCardPin(@AuthenticationPrincipal User user, @RequestBody UpdateCardPinRequest updateCardPinRequest) {
        return cardService.updateCardPin(user.getId(), updateCardPinRequest).toResponseEntity();
    }
}
