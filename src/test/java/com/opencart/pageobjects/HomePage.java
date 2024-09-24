package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import jdk.internal.org.jline.utils.Log;


//Page Object Model
public class HomePage extends BasePage{
	
//	Constructor
	public HomePage(WebDriver driver) {
		super(driver);
//		System.out.println("HomePage.HomePage() | "+driver.hashCode());
	}
	
//	Locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
//	Action methods
	public void clickMyAcount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
//		System.out.println("HomePage.clickLogin()");
	}
	
}





















