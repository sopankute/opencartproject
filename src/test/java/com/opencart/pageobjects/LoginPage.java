package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmailAddress(String email) {
		txtEmail.sendKeys(email);
//		System.out.println("LoginPage.setEmailAddress()");
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
//		System.out.println("LoginPage.setPassword()");
	}
	public void clickLogin() {
		btnLogin.click();
//		System.out.println("LoginPage.clickLogin()");
	}
}
