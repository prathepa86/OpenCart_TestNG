package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.RegistrationPage;
import testbase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{


	
	
	
	@Test(groups= {"Master","Reg"})
	public void verifyAccountRegistrationTest() {
		try {
		logger.info("***Startting TC001 AccountRegistrationTEst**");
		HomePage homePage=new HomePage(driver);
		homePage.clickAccount();
		logger.info("Clicked on MyAccount link");
		homePage.clickRegisterBtn();
		logger.info("Clicked on Register link");
		RegistrationPage registrationPage=new RegistrationPage(driver);
		logger.info("Providing Customer details");
		registrationPage.setFirstName("Prathepa");
		registrationPage.setLastName("Pasupathi");
		registrationPage.setEmail("preethi.pasupatko@gmail.com");
		registrationPage.setTelephone("9894328157");
		registrationPage.setPassword("HiAll@123");
		registrationPage.setConfirmPassword("HiAll@123");
		registrationPage.checkAgreePolicy();
		registrationPage.clickContinueBtn();
		//validate the your account has been created message
		logger.info("Validating expected message");
		String actualMsg = registrationPage.retrieveConfirmMsg();
		if(actualMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			//This will execute When the Assert fails
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e) {
			//This will execute when we get an exception
			Assert.fail();
			
		}
		logger.info("***Finished TC001 AccountRegistrationTEst**");
	}
	
	

}
