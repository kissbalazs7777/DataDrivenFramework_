package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.TestBase;

public class LogoutTests extends TestBase {
	
	@Test
    public void logOut() {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		doLogOut();
		assertEquals(driver.getCurrentUrl(), config.getProperty("pageUrl") + "/login.php");
    }
    
    @Test
    public void afterLogoutTryGoBackToPreviusPage() {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		doLogOut();
		goBack();
		assertEquals(driver.getCurrentUrl(), config.getProperty("pageUrl")  + "/login.php");
    }
    
}
