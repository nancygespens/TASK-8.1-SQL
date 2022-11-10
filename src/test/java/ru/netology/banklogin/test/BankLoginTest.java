package ru.netology.banklogin.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.banklogin.data.DataHelper;
import ru.netology.banklogin.data.SQLHelper;
import ru.netology.banklogin.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.banklogin.data.SQLHelper.cleanDatabase;

public class BankLoginTest {

  @AfterAll
  static void teardown() {
    cleanDatabase();
  }

  @Test
  void shouldSuccessfulLogin() {
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    verificationPage.verificationPageVisibility();
    var verificationCode = SQLHelper.getVerificationCode();
    verificationPage.validVerify(verificationCode.getCode());
  }
}
