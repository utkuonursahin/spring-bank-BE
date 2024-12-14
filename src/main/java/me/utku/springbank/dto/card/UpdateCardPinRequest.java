package me.utku.springbank.dto.card;

public record UpdateCardPinRequest(short oldPin, short newPin) {
}
