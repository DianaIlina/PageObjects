package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $x("//*/span[@data-test-id=\"login\"]//input").setValue(info.getLogin());
        $x("//*/span[@data-test-id=\"password\"]//input").setValue(info.getPassword());
        $x("//*/button[@data-test-id=\"action-login\"]").click();
        return new VerificationPage();
    }
}
