package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Master","Sanity"})
	public void verify_login(){
		
		logger.info("***** Starting TC_002 Login Test *****");
		
		try {
		//HomePage
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		//LoginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(prop.getProperty("email"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.clickLogin();
		
		
		//MyAccountPage
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean isAccountPageExists = myAccountPage.isMyAccountPageExists();
		
		Assert.assertTrue(isAccountPageExists);
		
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC_002 Login Test *****");
	}
}
