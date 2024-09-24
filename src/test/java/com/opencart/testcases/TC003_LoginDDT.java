package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.MyAccountPage;
import com.opencart.testclass.BaseClass;
import com.opencart.utilities.AllDataProvider;

//Data valid   -> 1. login Success -> Test pass -> logout
//				  2. login failed  -> Test failed
//Data Invalid -> 1. login Success -> Test failed -> logout
//				  2. login failed  -> Test pass

public class TC003_LoginDDT extends BaseClass {

	@Test(groups = "DataDriven",dataProvider = "loginData", dataProviderClass = AllDataProvider.class)
	public void verify_loginDDT(String email, String pwd, String expResult) {

		log.info("****** Starting TC003_LoginDDT ******");
		
		try {
			

//			Home Page
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAcount();
			homePage.clickLogin();

//			Login page
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmailAddress(email);
			loginPage.setPassword(pwd);
			loginPage.clickLogin();

//			My Account Page
			MyAccountPage myaccountPage = new MyAccountPage(driver);
			boolean targetpage = myaccountPage.isMyAccountTxtDisplay();

			if (expResult.equalsIgnoreCase("Valid")) {

				if (targetpage == true) {
					myaccountPage.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			} else if (expResult.equalsIgnoreCase("Invalid")) {

				if (targetpage == true) {
					myaccountPage.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

			
		}catch(Exception e) {
			Assert.fail();
		}
		
		
		log.info("****** Finished TC003_LoginDDT ******");
	}
}
