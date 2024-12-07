package me.utku.springbank.dto.card;

import java.math.BigDecimal;

public record UpdateCardSettingsRequest(
        short pin,
        BigDecimal cardLimit,
        boolean isOpenToInternet,
        boolean isOpenToInternationalTransactions,
        boolean isOpenToContactlessPayments
) {
}
