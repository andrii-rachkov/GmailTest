package org.testtask.app;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class GmailSpec {
    public static SelenideElement googleAccount = $(by("class", "gb_b gb_7a gb_R"));
    public static SelenideElement eMailErrorLabel = $("#errormsg_0_Email");
    public static SelenideElement eMailEditBox = $("#Email");
    public static SelenideElement passwordErrorLabel = $("#errormsg_0_Passwd");
    public static SelenideElement passwordEditBox = $("#Passwd");

    public static SelenideElement enterEmail(String email){
        return eMailEditBox.val(email);
    }

    public static SelenideElement enterPassword(String password) {
        return passwordEditBox.val(password);
    }
}
