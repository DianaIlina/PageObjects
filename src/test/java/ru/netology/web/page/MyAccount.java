package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$$;

public class MyAccount {
    public DataHelper.CardData[] cards;
    public MyAccount() {}

    public void getCardsArray() {
        ElementsCollection divs = $$(".list__item div");
        int count = divs.size();
        DataHelper.CardData[] cards = new DataHelper.CardData[count];
        for (int i=0; i<count; i++) {
            String cardId = divs.get(i).getAttribute("data-test-id");
            String numberTail = extractNumberTail(divs.get(i).text());
            int balance = extractBalance(divs.get(i).text());
            String number = DataHelper.findCardNumberFull(numberTail);
            cards[i] = new DataHelper.CardData(cardId, balance, number);
        }
        this.cards = cards;
    }

    private String extractNumberTail(String text) {
        String numberStart = "**** **** **** ";
        int startNum = text.indexOf(numberStart);
        String numberFinish = ", ";
        int finishNum = text.indexOf(numberFinish);
        String valueNum = text.substring(startNum + numberStart.length(), finishNum);
        return valueNum;
    }

    private int extractBalance(String text) {
        String balanceStart = ", баланс: ";
        int startBal = text.indexOf(balanceStart);
        String balanceFinish = " р.";
        int finishBal = text.indexOf(balanceFinish);
        String valueBal = text.substring(startBal + balanceStart.length(), finishBal);
        return Integer.parseInt(valueBal);
    }
}
