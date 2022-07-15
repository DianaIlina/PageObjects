package ru.netology.web.page;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CardPage {
    public CardPage() {
        $x("//*/h2[@data-test-id=\"dashboard\"]")
                .shouldHave(visible, Duration.ofSeconds(15))
                .shouldHave(text("Личный кабинет"));    }
    public void Transfer(String receiveCardId, int transferringAmount, String withdrawCardNumberFull) {
        $x("//*/div[@data-test-id=\"" + receiveCardId + "\"]//button").click();
        $x("//*/span[@data-test-id=\"amount\"]//input").setValue(String.valueOf(transferringAmount));
        $x("//*/span[@data-test-id=\"from\"]//input").setValue(withdrawCardNumberFull);
        $x("//*/button[@data-test-id=\"action-transfer\"]").click();
    }
}
