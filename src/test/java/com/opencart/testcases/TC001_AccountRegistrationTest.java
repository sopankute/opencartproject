package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.pageobjects.AccountRegistrationPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.testclass.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {

		try {
			log.info("Start TC001_AccountRegistrationTest");
			HomePage homepage = new HomePage(driver);
			homepage.clickMyAcount();
			log.info("user clicked on MyAcount");
			homepage.clickRegister();
			log.info("user clicked on Register");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.setFirstName(randomEmail().toUpperCase());
			regpage.setLastName(randomEmail().toUpperCase());
			regpage.setEmail(randomEmail() + "@gmail.com");
			regpage.setTelephone(randomTelephone());

			String password = randomPassword();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			log.info("user clicked on Continue");

			String confmsg = regpage.getConfirmationMsg();
			log.info("validating confirmation msg");
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}catch(Exception e) {
			log.info("Test Failed");
			log.debug("debug logs");
			Assert.fail();
		}
		log.info("Test Finished");
	}
}
