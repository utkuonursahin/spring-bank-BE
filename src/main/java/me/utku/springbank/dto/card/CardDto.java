package me.utku.springbank.dto.card;

import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.model.Card;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link me.utku.springbank.model.Card}
 */
public record CardDto(UUID id,
                      String cardNumber,
                      UserDto owner,
                      BigDecimal termExpense,
                      BigDecimal cardLimit,
                      boolean isOpenToInternet,
                      boolean isOpenToInternationalTransactions,
                      boolean isOpenToContactlessPayments) implements BaseDto<Card> {
}