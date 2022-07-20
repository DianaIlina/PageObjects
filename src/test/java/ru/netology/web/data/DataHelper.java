package ru.netology.web.data;

import com.github.javafaker.Faker;
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

    @Value
    public static class CardData {
        String id;
        int balance;
        String number;
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

    public static String findCardNumberFull(String tail) {
        for (DataHelper.CardDetails card : DataHelper.getCardNumbers()) {
            if (card.getCardNumberFull().substring(card.getCardNumberFull().length()-4).equals(tail)) {
                return card.getCardNumberFull();
            }
        }
        return null;
    }

    public static String[] findWithdrawingCardNumbers(DataHelper.CardData[] cards, String removeCard) {
        String[] withdrawingCards = new String[cards.length-1];
        int copyToIndex = 0;
        for (DataHelper.CardData card : cards) {
            if (!card.getNumber().equals(removeCard)) {
                withdrawingCards[copyToIndex] = card.getNumber();
                copyToIndex++;
            }
        }
        return withdrawingCards;
    }

    public static int calculateAmount(int maxAmount) {
        Faker faker = new Faker();
        int amount = faker.number().numberBetween(1, maxAmount);
        return amount;
    }
}
