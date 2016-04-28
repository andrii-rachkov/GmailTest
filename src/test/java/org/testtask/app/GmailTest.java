package org.testtask.app;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GmailTest {

    @Test
    public void verifyGmailAuthorization() {
        timeout = 10000;
        baseUrl = "http://gmail.com";
        startMaximized = false;

        open("/");
        login();
        assertThat("Verify that login authorization was successful.",
                GmailSpec.googleAccount.waitUntil(appears, 20000).isDisplayed(), is(true));
        closeWebDriver();
    }

    private void login() {
        GmailSpec.enterEmail(System.getProperty("gmail.username", "enter-your-gmail-username")).pressEnter();
        assertThat("Verify that mail entered correctly.", GmailSpec.eMailErrorLabel.isDisplayed(), is(false));
        GmailSpec.enterPassword(System.getProperty("gmail.password", "enter-your-gmail-password")).pressEnter();
        assertThat("Verify that password entered correctly.", GmailSpec.passwordErrorLabel.isDisplayed(), is(false));
    }

}
