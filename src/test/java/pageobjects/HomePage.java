package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement accBtn;
	
	@FindBy(linkText ="Register")
	WebElement RegisterBtn;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement loginBtn;
	
	
	public void clickAccount() {
		accBtn.click();
	}
	
	public void clickRegisterBtn() {
		RegisterBtn.click();
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}

	 

}
