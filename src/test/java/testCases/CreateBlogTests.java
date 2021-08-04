package testCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class CreateBlogTests extends TestBase {
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void createBlogSuccess(String title, String description, String url, String filePath, String text) {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		fillFieldsToCreateBlog(title, description, url, filePath, text);
		softAssert.assertTrue(isElementPresent(By.id(locators.getProperty("creBlogCreatedSuccMsg_ID"))));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		softAssert.assertTrue(isElementPresent(By.xpath("//p[@id='" + url + "blogtitle'][text()='" + title + "']")));
		softAssert.assertTrue(isElementPresent(By.xpath("//p[@id='" + url + "blogdesc'][text()='" + description + "']")));
		softAssert.assertTrue(isElementPresent(By.xpath("//a[@id='" + url + "blogurl'][@href='blog/" + url + ".html']")));
		elementActions.click("adminPageContinueReadArrow_XPATH");
		softAssert.assertTrue(isElementPresent(By.xpath("//p[text()='" + text + "']")));
		softAssert.assertAll();
		goBack();
		deleteFirstBlog();
		doLogOut();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void createBlogLeavingBlankField(String title, String description, String url, String filePath, String text) {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		fillFieldsToCreateBlog(title, description, url, filePath, text);
		assertTrue(elementActions.findElementBy("bcwThereAreBlankFieldsError_ID").isDisplayed());
		elementActions.click("bcwCloseBlogEditorButton_XPATH");
		doLogOut();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void createBlogUrlAlreadyTaken(String title, String description, String url, String filePath, String text) {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		fillFieldsToCreateBlog(title, description, url, filePath, text);
		assertTrue(elementActions.findElementBy("bcwUrlTakenMsg_ID").isDisplayed());
		elementActions.click("bcwCloseBlogEditorButton_XPATH");
		doLogOut();
    }
	
}
