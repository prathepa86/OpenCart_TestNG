package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass{
	//Mapping of the dataprovider annotation with our test case using name
	//By dataproviderclass we can mention where exactly our dataprovider class
	//It will be useful when our dataprovider is present in different package
	
	
	@Test(groups="data driver",dataProvider = "LoginData",dataProviderClass = DataProviders.class)
	public void VerifyLoginUsingDataProviders(String email,String password,String expResult) {
		logger.info("***Started TC003_LoginDataDrivenTest**");
		try {
			HomePage homePage=new HomePage(driver);
			homePage.clickAccount();
			homePage.clickLoginBtn();
			logger.info("Providing values for email and password");
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setEmailAddress(email);
			loginPage.setPassword(password);
			loginPage.clickLoginBtn();
			
			//Validation
			//1.Data is valid and login is successful then the test case is passed
		    MyAccountPage myAccPage=new MyAccountPage(driver);
		    boolean myAccountPageExists = myAccPage.isMyAccountPageExists();
		    //Passing Valid Email and Password
		    if(expResult.equalsIgnoreCase("valid")) {
		    	//Successfully landed on the My Account page
		    	if(myAccountPageExists==true) {
		    		myAccPage.clickLogOutBtn();
		    		Assert.assertTrue(true);
		    		logger.info("Validation using valid data is passed");
		    	}else {
		    		//2.Data is valid and login is unsuccessful then the test case is failed	    		
		    		Assert.assertTrue(false);
		    	}
		    }
		//3.If data is invalid and the login is successful then the test case is Failed
		//invalid data
		    if(expResult.equalsIgnoreCase("invalid")) {
		    	//Login is successful
			if(myAccountPageExists==true) {
				myAccPage.clickLogOutBtn();
				//TestCase is failed
				Assert.assertTrue(false);
			}else {
				//4.If data is invalid and the login is unsuccessful then the test case is passed
				//Test case is passed
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***Finished TC003_LoginDataDrivenTest**");
	}

}
