package org.testtask.app;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GmailTest {
    private static SelenideElement googleAccount = $(by("class","gb_b gb_7a gb_R"));
    private static SelenideElement errorMail = $("#errormsg_0_Email");
    private static SelenideElement eMail = $("#Email");
    private static SelenideElement errorPwd = $("#errormsg_0_Passwd");
    private static SelenideElement pwd = $("#Passwd");

    @Test
    public void verifyGmailAuthorization() {
        timeout = 10000;
        baseUrl = "http://gmail.com";
        startMaximized = false;

        open("/");
        login();
        assertThat("Verify that login authorization was successful.",
                googleAccount.waitUntil(appears, 20000).isDisplayed(), is(true));
        closeWebDriver();
    }

    private void login() {
        eMail.val(System.getProperty("gmail.username", "enter-your-gmail-username")).pressEnter();
        assertThat("Verify that mail entered correctly.", errorMail.isDisplayed(), is(false));
        pwd.val(System.getProperty("gmail.password", "enter-your-gmail-password")).pressEnter();
        assertThat("Verify that password entered correctly.", errorPwd.isDisplayed(), is(false));
    }

}
