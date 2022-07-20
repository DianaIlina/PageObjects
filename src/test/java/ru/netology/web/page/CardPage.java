package ru.netology.web.page;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CardPage {
    public CardPage() {
        $x("//*/h2[@data-test-id=\"dashboard\"]")
                .shouldHave(visible, Duration.ofSeconds(15))
                .shouldHave(text("Личный кабинет"));    }
    public TransferPage topUp(String receiveCardId) {
        $x("//*/div[@data-test-id=\"" + receiveCardId + "\"]//button").click();
        return new TransferPage();
    }
}
