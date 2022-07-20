package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MoneyTransferTest {
    CardPage cardPage;

    @BeforeEach
    void LogInBeforeTest() {
        Configuration.headless = true;

        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode(authInfo);

        cardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void transferMoney() {
        MyAccount myAccount = new MyAccount();

        myAccount.getCardsArray();
        DataHelper.CardData receiveCard = myAccount.cards[0];

        String withdrawCardNumber = DataHelper.findWithdrawingCardNumbers(myAccount.cards, receiveCard.getNumber())[0];
        DataHelper.CardData withdrawCard = DataHelper.findCardByNumber(myAccount.cards, withdrawCardNumber);

        assertNotNull(withdrawCard);

        int transferringAmount = DataHelper.calculateAmount(withdrawCard.getBalance());

        int oldReceiveCardBalance = receiveCard.getBalance();
        int oldWithdrawCardBalance = withdrawCard.getBalance();

        TransferPage transferPage = cardPage.topUp(receiveCard.getId());

        transferPage.transfer(transferringAmount, withdrawCardNumber);

        myAccount.getCardsArray();

        assertEquals(oldReceiveCardBalance + transferringAmount,
                myAccount.cards[0].getBalance());
        assertEquals(oldWithdrawCardBalance - transferringAmount,
                DataHelper.findCardByNumber(myAccount.cards, withdrawCardNumber).getBalance());
    }
}
