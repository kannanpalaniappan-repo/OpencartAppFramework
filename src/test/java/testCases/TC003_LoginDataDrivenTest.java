package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {


	@Test(dataProvider= "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")
	public void loginDDT(String email, String password, String res) {
		
		logger.info("***** Starting TC_003 LoginDDT *****");
		
		try {
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		//LoginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(email);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		
		
		//MyAccountPage
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean isAccountPageExists = myAccountPage.isMyAccountPageExists();
		
		
		if(res.equalsIgnoreCase("Valid")) {
			if(isAccountPageExists==true) {
				myAccountPage.clickLogout();
				Assert.assertTrue(true);
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		
		if(res.equalsIgnoreCase("InValid")) {
			if(isAccountPageExists==true) {
				
				myAccountPage.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
		
		
		logger.info("***** Finished TC_003 Login Test *****");
	}
}
