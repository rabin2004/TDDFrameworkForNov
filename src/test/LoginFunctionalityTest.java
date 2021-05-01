package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Main;
import pageFactory.HomePage;
import pageFactory.LoginSuccessPage;

public class LoginFunctionalityTest extends Main{
	HomePage hp;
	LoginSuccessPage lsp;
	
	@BeforeMethod
	public void loadUrl() {
		initialization();
		hp = new HomePage();
		lsp = new LoginSuccessPage();
	}
	
	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}
	
	@Test(dataProvider="Positive login test data")
	public void positiveLoginTest(String username, String password) {
		hp.enterUsername(username);
		hp.enterPassword(password);
		hp.clickSubmitBtn();
		Assert.assertTrue(lsp.validateLoginSuccessMsg());
		Assert.assertEquals(lsp.getLoginSuccessPageTitle(), prop.getProperty("loginPageTitle"));
	}
	
	@DataProvider(name = "Positive login test data")
	public Object[][] testData() {
		Object[][] data = {{"test123","123"},{"test","123"}};
		return data;
	}

}
