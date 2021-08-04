package testCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class EditBlogTests extends TestBase {
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void editBlogSuccess(String urlToEdit) {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageEditBlogButton_ID");
		elementActions.clearInput("bewBlogUrlInput_ID");
		elementActions.type("bewBlogUrlInput_ID", urlToEdit, true);
		elementActions.type("bewBlogUploadImageInput_ID", System.getProperty("user.dir") + excelReader.getCellData("createBlogSuccess", 1, 3), false);
		elementActions.click("bewSaveBlogButton_XPATH");
		softAssert.assertTrue(isElementPresent(By.xpath("//a[@id='" + urlToEdit + "blogurl'][@href='blog/" + urlToEdit + ".html']")));
		elementActions.click("adminPageContinueReadArrow_XPATH");
		softAssert.assertEquals(driver.getCurrentUrl(), config.getProperty("pageUrl") + "/blog/" + urlToEdit);
		goBack();
		deleteFirstBlog();
		doLogOut();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void editBlogUnSuccessTakenUrl(String urlToEdit) {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageEditBlogButton_ID");
		elementActions.clearInput("bewBlogUrlInput_ID");
		elementActions.type("bewBlogUrlInput_ID", urlToEdit, true);
		elementActions.type("bewBlogUploadImageInput_ID", System.getProperty("user.dir") + excelReader.getCellData("createBlogSuccess", 1, 3), false);
		elementActions.click("bewSaveBlogButton_XPATH");
		assertTrue(elementActions.findElementBy("bewUrlTakenMsg_ID").isDisplayed());
		elementActions.click("bewCloseBlogEditorButton_XPATH");
		deleteFirstBlog();
		doLogOut();
    }
	
	@Test
    public void editBlogUnSuccessBlankField() {
		doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageEditBlogButton_ID");
		elementActions.type("bewBlogUploadImageInput_ID", System.getProperty("user.dir") + excelReader.getCellData("createBlogSuccess", 1, 3), false);
		elementActions.clearInput("bewBlogUrlInput_ID");
		elementActions.click("bewSaveBlogButton_XPATH");
		assertTrue(elementActions.findElementBy("bewThereAreBlankFieldsError_ID").isDisplayed());
		elementActions.click("bewCloseBlogEditorButton_XPATH");
		deleteFirstBlog();
		doLogOut();
    }
}
