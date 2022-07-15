package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    CardPage cardPage;

    @BeforeEach
    void LogInBeforeTest() {
        Configuration.headless = true;

        Login login = open("http://localhost:9999", Login.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        VerificationPage verificationPage = login.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode(authInfo);

        cardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void transferMoney() {
        Configuration.headless = true;

        DataHelper.CardData[] cards = MyAccount.getCardsArray();
        DataHelper.CardData receiveCard = cards[0];

        String withdrawCardNumber = MyAccount.findWithdrawingCardNumbers(cards, receiveCard.getNumber())[0];
        DataHelper.CardData withdrawCard = DataHelper.findCardByNumber(cards, withdrawCardNumber);

        int transferringAmount = MyAccount.calculateAmount(withdrawCard.getBalance());

        int oldReceiveCardBalance = receiveCard.getBalance();
        int oldWithdrawCardBalance = withdrawCard.getBalance();

        cardPage.Transfer(receiveCard.getId(), transferringAmount, withdrawCardNumber);

        cards = MyAccount.getCardsArray();

        assert oldReceiveCardBalance + transferringAmount == cards[0].getBalance();
        assert oldWithdrawCardBalance - transferringAmount == DataHelper.findCardByNumber(cards, withdrawCardNumber).getBalance();
    }
}
