package com.ontraport.app.selenium.tests;


import org.junit.Test;
import org.openqa.selenium.By;


import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteAllSequences extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testDeleteAllSequences() throws Exception {
		
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		appUtilities.navigateTo (driver, "Contacts==Sequences");
		//Delete All Sequences
		do {
			driver.findElement(By.xpath("//thead[@class='ussr-component-collection-head']//a")).click();
			driver.findElement(By.linkText("Delete Sequences")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='ussr-dialog-buttons']//span[normalize-space(text())='Ok']/parent::button")).click();
			Thread.sleep(3000);			
		} while (!driver.getPageSource().contains("There are no items to display"));
		appUtilities.logOutOfApp(driver);
		
		

}
}
