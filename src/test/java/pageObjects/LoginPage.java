package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.CSS, using = "#input-email")
	WebElement inpEmail;
	
	@FindBy(how = How.CSS, using = "#input-password")
	WebElement inpPassword;
	
	@FindBy(how = How.CSS, using = "input[type='submit']")
	WebElement btnLogin;
	
	
	public void setEmail(String email) {
		inpEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		inpPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

}
