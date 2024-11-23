package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")WebElement msgHeading;
	@FindBy(linkText = "Logout")WebElement logoutBtn;
	
	public boolean isMyAccountPageExists() {
		try {
			
			//If the page is not displayed it will throw an exception
			return msgHeading.isDisplayed()	;
			//REturn false if the page is not there
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogOutBtn() {
		logoutBtn.click();
	}
	
	
}
