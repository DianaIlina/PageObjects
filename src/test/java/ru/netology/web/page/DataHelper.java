package ru.netology.web.page;

import lombok.Data;
import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        public String login;
        public String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo ("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Data
    public static class CardData {
        String id;
        int balance;
        String number;

        public CardData(String id, int balance, String number) {
            this.id = id;
            this.balance = balance;
            this.number = number;
        }
    }


    public static CardData findCardByID(CardData[] cards, String id) {
        for (CardData card : cards) {
            if(card.getId().equals(id)) {
                return card;
            }
        }
        return null;
    }

    public static CardData findCardByNumber(CardData[] cards, String number) {
        for (CardData card : cards) {
            if(card.getNumber().equals(number)) {
                return card;
            }
        }
        return null;
    }


    @Value
    public static class CardDetails {
        String cardNumberFull;
        int balance;
    }

    public static CardDetails[] getCardNumbers() {
        CardDetails Card1 = new CardDetails("5559000000000001", 10000);
        CardDetails Card2 = new CardDetails("5559000000000002", 10000);

        CardDetails[] Result = {Card1, Card2};
        return Result;
    }
}
