package ru.netology.web.page;

import static com.codeborne.selenide.Selenide.$x;

public class CardPage {

    public void Transfer(String receiveCardId, int transferringAmount, String withdrawCardNumberFull) {
        $x("//*/div[@data-test-id=\"" + receiveCardId + "\"]//button").click();
        $x("//*/span[@data-test-id=\"amount\"]//input").setValue(String.valueOf(transferringAmount));
        $x("//*/span[@data-test-id=\"from\"]//input").setValue(withdrawCardNumberFull);
        $x("//*/button[@data-test-id=\"action-transfer\"]").click();
    }
}
