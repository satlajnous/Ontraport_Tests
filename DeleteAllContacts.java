package com.ontraport.app.selenium.tests;


import org.junit.Test;
import org.openqa.selenium.By;


import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteAllContacts extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testDeleteAllContacts() throws Exception {
		
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		appUtilities.navigateTo (driver, "Contacts");
		//Delete All contacts
		do {
			driver.findElement(By.xpath("//thead[@class='ussr-component-collection-head']//a")).click();
			driver.findElement(By.linkText("Delete Contacts")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='ussr-dialog-buttons']//span[normalize-space(text())='Ok']/parent::button")).click();
			Thread.sleep(3000);			
		} while (!driver.getPageSource().contains("No contacts yet... let's create some!"));
		appUtilities.logOutOfApp(driver);
		
		

}
}
