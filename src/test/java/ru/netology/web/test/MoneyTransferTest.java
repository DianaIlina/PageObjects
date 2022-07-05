package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.page.DataHelper;
import ru.netology.web.page.Login;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @Test
    void shouldLogIn() {
        open("http://localhost:9999");
        var login = open("http://localhost:9999", Login.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = login.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);

        $x("//*/h2[@data-test-id=\"dashboard\"]")
                .shouldHave(visible, Duration.ofSeconds(15))
                .shouldHave(text("Личный кабинет"));
    }
}
