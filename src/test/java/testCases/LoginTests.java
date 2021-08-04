package testCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

import static org.testng.Assert.assertEquals;

public class LoginTests extends TestBase {
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void successfulLogin(String email, String password, String isLoginSuccessful) {
		doLogIn(email, password);
        assertEquals(isElementPresent(By.xpath(locators.getProperty("adminPageLogOutButton_XPATH"))), Boolean.parseBoolean(isLoginSuccessful));
        doLogOut();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
	public void unsuccessfulLogin(String email, String password, String isLoginSuccessful) throws InterruptedException {
		doLogIn(email, password);
		assertEquals(isElementPresent(By.xpath(locators.getProperty("loginErrorMessage_XPATH"))), Boolean.parseBoolean(isLoginSuccessful));
    }
	
}
