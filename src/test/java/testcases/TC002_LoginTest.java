package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin() {
		logger.info("***TC002_LoginTest Started****");
		try {
		HomePage homePage=new HomePage(driver);
		homePage.clickAccount();
		logger.info("Account link clicked");
		homePage.clickLoginBtn();
		logger.info("Login link clicked");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setEmailAddress(properties.getProperty("email"));
		loginPage.setPassword(properties.getProperty("password"));
		loginPage.clickLoginBtn();
		logger.info("Successfully entered login details");
		MyAccountPage myAccPage=new MyAccountPage(driver);
		boolean myAccountPageExists = myAccPage.isMyAccountPageExists();
		logger.info("Verification of MyAccount page");
		Assert.assertEquals(myAccountPageExists, true,"Login Failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("***TC002_Login test completed***");
	}

}
