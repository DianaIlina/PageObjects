package ru.netology.web.page;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    public TransferPage() {
        $x("//*/h2[@data-test-id=\"dashboard\"]")
                .shouldHave(visible, Duration.ofSeconds(15))
                .shouldHave(text("Личный кабинет"));    }
    public CardPage transfer(int transferringAmount, String withdrawCardNumberFull) {
        $x("//*/span[@data-test-id=\"amount\"]//input").setValue(String.valueOf(transferringAmount));
        $x("//*/span[@data-test-id=\"from\"]//input").setValue(withdrawCardNumberFull);
        $x("//*/button[@data-test-id=\"action-transfer\"]").click();
        return new CardPage();
    }
}
