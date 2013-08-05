package com.ontraport.app.selenium.tests;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class TryToFailUsageAgreement extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testFailUsageAgreement() throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		
		driver.findElement(By.xpath("//li//span[normalize-space(text())=\"Settings\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class=\"ussr-section\"]//li/a[@href=\"#!/contact/import\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class=\"ussr-form-column\"]//tr/td[2]/span/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[normalize-space(text())=\"Next: Import Settings\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[normalize-space(text())=\"Next: Import Settings\"]")).click();
		Thread.sleep(1000);
		
		//Asserting for pop-up presence
		Assert.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//div[@class='ussr-dialog-content-wrapper " +
				"ussr-component-shadow-content ussr-shadow-outset ussr-border-solid-all red ussr-corner-all']")));
		
		String msgfromapp = driver.findElement(By.xpath("//div[@class='ussr-dialog-content-wrapper " +
		"ussr-component-shadow-content ussr-shadow-outset ussr-border-solid-all red ussr-corner-all']")).getText().trim();
		
		String expectedmsg = "Errors have prevented you from advancing to the next step. Please make sure you have accepted (checked) all the terms of the Usage Agreement";
		Assert.assertTrue( msgfromapp.toLowerCase().contains(expectedmsg.trim().toLowerCase()),"Pop up Message verification");
		
		
	}	
}
