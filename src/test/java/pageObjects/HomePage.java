package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how=How.XPATH, using="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(how=How.XPATH, using="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(how=How.LINK_TEXT, using="Login")
	WebElement lnkLogin;

	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
}
