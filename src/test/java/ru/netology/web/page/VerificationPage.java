package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    public VerificationPage() {
        $x("//*/span[@data-test-id=\"code\"]//input").shouldHave(Condition.visible);
    }

    public CardPage validVerify(DataHelper.VerificationCode verificationCode) {
        $x("//*/span[@data-test-id=\"code\"]//input").setValue(verificationCode.getCode());
        $x("//*/button[@data-test-id=\"action-verify\"]").click();
        return new CardPage();
    }

}
