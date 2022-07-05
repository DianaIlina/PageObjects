package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private SelenideElement codeField = $x("//*/span[@data-test-id=\"code\"]//input");
    private SelenideElement verifyButton = $x("//*/button[@data-test-id=\"action-verify\"]");

    public VerificationPage() {
        codeField.shouldHave(Condition.visible);
    }

    public String validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new String();
    }

}
