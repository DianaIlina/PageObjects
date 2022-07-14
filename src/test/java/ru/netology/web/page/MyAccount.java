package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class MyAccount {
    private static final String balanceStart = ", баланс: ";
    private static final String balanceFinish = " р.";

    private static final String numberStart = "**** **** **** ";
    private static final String numberFinish = ", ";


    public MyAccount() {}

    public static DataHelper.CardData[] getCardsArray() {
        ElementsCollection divs = $$(".list__item div");
        int count = divs.size();
        DataHelper.CardData[] cards = new DataHelper.CardData[count];
        for (int i=0; i<count; i++) {
            String cardId = divs.get(i).getAttribute("data-test-id");
            String numberTail = extractNumberTail(divs.get(i).text());
            int balance = extractBalance(divs.get(i).text());
            String number = findCardNumberFull(numberTail);
            cards[i] = new DataHelper.CardData(cardId, balance, number);
        }
        return cards;
    }

    private static String extractNumberTail(String text) {
        val startNum = text.indexOf(numberStart);
        val finishNum = text.indexOf(numberFinish);
        val valueNum = text.substring(startNum + numberStart.length(), finishNum);
        return valueNum;
    }

    private static int extractBalance(String text) {
        val startBal = text.indexOf(balanceStart);
        val finishBal = text.indexOf(balanceFinish);
        val valueBal = text.substring(startBal + balanceStart.length(), finishBal);
        return Integer.parseInt(valueBal);
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



//    public static int checkBalance(String receiveCard) {
//        ElementsCollection divs = $$(".list__item div");
//
//        int balance = MyAccount.extractBalance(receiveCard);
//        return balance;
//    }



}
