package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.MyAccountPage;
import com.opencart.testclass.BaseClass;

public class TC002_UserLoginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void verify_user_login() {
		
		log.info("****** Starting TC002_UserLoginTest ******");
		try {
//			Home Page
			HomePage homePage=new HomePage(driver);
			homePage.clickMyAcount();
			homePage.clickLogin();
			
//			Login page
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setEmailAddress(prop.getProperty("email"));
			loginPage.setPassword(prop.getProperty("pwd"));
			loginPage.clickLogin();
			log.info("clicked on login button");
			
//			My Account Page
			MyAccountPage myaccountPage=new MyAccountPage(driver);
			boolean targetpage = myaccountPage.isMyAccountTxtDisplay();
		
			Assert.assertTrue(targetpage);	// Assert.assertEquals(targetpage, true, "Login Failed");
			log.info("Login Successfully"); 
						
		}catch(Exception e) {
			Assert.fail();
			log.info("Login failed");
		}
		log.info("****** finished TC002_UserLoginTest ******");
		
		
	}
}
