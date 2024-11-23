package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-email")WebElement Emailtxt;
	@FindBy(id="input-password")WebElement PasswordTxt;
	@FindBy(xpath="//input[@type='submit']")WebElement loginBtn;
	
	public void setEmailAddress(String email) {
		Emailtxt.sendKeys(email);
	}
	public void setPassword(String password) {
		PasswordTxt.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
	

}
