package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups= {"Regression","Master"})
	public void verify_account_registration(){
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		homePage.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details");
		accountRegistrationPage.setFirstName(randomAlphabetic().toUpperCase());
		accountRegistrationPage.setLastName(randomAlphabetic().toUpperCase());
		accountRegistrationPage.setEmail(randomAlphabetic()+"@gmail.com");
		accountRegistrationPage.setTelephone(randomNumeric());
		String password = randomAlphaNumeric();
		System.out.println(password);
		accountRegistrationPage.setPassword(password);
		accountRegistrationPage.setConfirmPassword(password);
		accountRegistrationPage.clickAgree();
		accountRegistrationPage.clickContinue();
		
		logger.info("Validating Expected Message");
		String confirmMessage = accountRegistrationPage.getMessageConfirmation();
		
		if(confirmMessage.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("test failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
			
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		//Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
		
		
	}
}
