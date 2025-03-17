package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='input-firstname']")
	WebElement inpFirstname;

	@FindBy(how = How.XPATH, using = "//input[@id='input-lastname']")
	WebElement inpLastname;

	@FindBy(how = How.XPATH, using = "//input[@id='input-email']")
	WebElement inpEmail;

	@FindBy(how = How.XPATH, using = "//input[@id='input-telephone']")
	WebElement inpTelephone;

	@FindBy(how = How.XPATH, using = "//input[@id='input-password']")
	WebElement inpPassword;

	@FindBy(how = How.XPATH, using = "//input[@id='input-confirm']")
	WebElement inpConfirmPassword;

	@FindBy(how = How.XPATH, using = "//input[@name='agree']")
	WebElement chkAgree;

	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	WebElement btnContinue;

	@FindBy(how = How.XPATH, using = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstName(String firstName) {
		inpFirstname.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		inpLastname.sendKeys(lastName);
	}

	public void setEmail(String email) {
		inpEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		inpTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
		inpPassword.sendKeys(password);
	}

	public void setConfirmPassword(String confirmPassword) {
		inpConfirmPassword.sendKeys(confirmPassword);
	}

	public void clickAgree() {
		chkAgree.click();
	}

	public void clickContinue() {
		btnContinue.click();
	}

	public String getMessageConfirmation() {
		try {
			return msgConfirmation.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}

}
