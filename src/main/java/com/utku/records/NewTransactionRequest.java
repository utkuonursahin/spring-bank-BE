package com.utku.records;

public record NewTransactionRequest(
        String type,
        Integer senderID,
        Integer receiverID,
        Integer amount) {
}
