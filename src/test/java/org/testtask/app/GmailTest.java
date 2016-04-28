package org.testtask.app;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GmailTest {

    @DataProvider(name = "GmailTest")
    public Object[][] createTestData() {
        return new Object[][] {
            { "IncorrectMail", "IncorrectPassword" }
        };
    }

    @BeforeMethod
    public void setup(){
        timeout = 10000;
        baseUrl = "http://gmail.com";
        startMaximized = false;
    }

    @Test(dataProvider = "GmailTest")
    public void verifyGmailAuthorization(String email, String password) {
        open("/");

        GmailSpec.enterEmail(email).pressEnter();
        assertThat("Verify that mail entered correctly.",
                GmailSpec.eMailErrorLabel.waitUntil(disappears, 2000).isDisplayed(), is(false));

        GmailSpec.enterPassword(password).pressEnter();
        assertThat("Verify that password entered correctly.",
                GmailSpec.passwordErrorLabel.waitUntil(disappears, 2000).exists(), is(false));

        assertThat("Verify that login authorization was successful.",
                GmailSpec.googleAccount.waitUntil(appears, 20000).isDisplayed(), is(true));
    }

    @AfterMethod
    public void cleanup(){
        closeWebDriver();
    }

}
