package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Main;
import pageFactory.HomePage;
import pageFactory.RegisterPage;
import pageFactory.RegisterSuccessPage;

public class RegistrationFunctionalityTest extends Main{
	
	HomePage hp;
	RegisterPage rp;
	RegisterSuccessPage rsp;

	@BeforeMethod
	public void loadUrl() {
		initialization();
		hp = new HomePage();
		rp = new RegisterPage();
		rsp = new RegisterSuccessPage();
		hp.clickRegisterLink();
	}
	
	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}
	
	@Test (dataProvider="Postive Registration test data")
	public void positiveRegistrationFunctionalityTest(String firstName,String lastName,
			String phoneNumber,String emailAddress,String userName,String password) {
		rp.enterFirstName(firstName);
		rp.enterLastName(lastName);
		rp.enterPhoneNumber(phoneNumber);
		rp.enterEmailAdd(emailAddress);
		rp.enterUserName(userName);
		rp.enterPassword(password);
		rp.enterConfirmPassword(password);
		rp.clickSubmitBtn();
		
		Assert.assertTrue(rsp.validateRegistrationSuccessMsg());
		Assert.assertEquals(rsp.getRegistrationSuccessMsg(), "Note: Your user name is "+userName+".");
	}
	
	@DataProvider (name="Postive Registration test data")
	public Object[][] getData() {
		Object[][] data = {	{"David","A.","123456789","david@gmail.com","david12","1234"},
							{"John","B.","123456789","john@gmail.com","john12","1234"}};
		return data;
	}

}
