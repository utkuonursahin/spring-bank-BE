package me.utku.springbank.transaction.dto;

import java.util.List;

public record TransactionPageDto(
        List<TransactionDto> transactions,
        int size,
        int page,
        int totalPages,
        long totalElements
) {
}
