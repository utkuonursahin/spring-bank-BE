package me.utku.transaction;

public record NewTransactionRequest(
        String type,
        Integer senderID,
        Integer receiverID,
        Integer amount) {
}
