package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "input-firstname") WebElement txtFirstName;
	@FindBy(id ="input-lastname")WebElement txtLastName;
	@FindBy(id = "input-email")WebElement txtEmail;
	@FindBy(id="input-telephone")WebElement txtTelephone;
	@FindBy(id="input-password")WebElement txtPassword;
	@FindBy(id="input-confirm")WebElement txtConfirmPassword;
	@FindBy(name="agree")WebElement chkAgree;
	@FindBy(xpath="//input[@type='submit']")WebElement btnContinue;
	@FindBy(xpath="//div[@id='content']/h1")WebElement msgConfirmation;
	
	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	public void checkAgreePolicy() {
		chkAgree.click();
	}
	public void clickContinueBtn() {
		btnContinue.click();
	}
	public String retrieveConfirmMsg() {
		try {
		 String msg = msgConfirmation.getText();
		 return msg;
		}catch(Exception e) {
			return e.getMessage();
		}
	}

}
