package com.utku.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Random;

@Entity
@Data
public class Transaction {
    @Id
    private String transactionID;
    private String type;
    private Integer senderID;
    private Integer receiverID;
    private Integer amount;

    public Transaction() {}

    ////// METHODS //////
    public String generateTransactionID(){
        Random random = new Random();
        int f4d = random.nextInt(10000);
        char[] chars = {'A','B','C','D','E','F','X','Y','Z','W','a','b','c','d','e','f','x','y','z','w'};
        StringBuilder sb = new StringBuilder();
        Random random1 = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random1.nextInt(chars.length)];
            sb.append(c);
        }
        String str = sb.toString();
        int s4d = random.nextInt(10000);
        return f4d + "-" + str + "-" +s4d;
    }

    public boolean isTransactionValid(User user){
        String type = this.type.toLowerCase();
        if(this.amount <= 0) return false;
        else if(type.equals("deposit") || type.equals("withdraw")){
            return true;
        } else if(type.equals("transfer") && this.senderID != this.receiverID){
            return true;
        } else if (type.equals("transfer") && user.getDebt() > 0){
            return true;
        } else {
            return false;
        }
    }
}
