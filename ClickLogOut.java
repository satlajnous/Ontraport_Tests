package com.ontraport.app.selenium.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class ClickLogOut extends OntraportFirefoxTest {
AppUtilities appUtilities = new AppUtilities();
	
	
	@Test
	public void testClickPackage() throws Exception {
		//baseUrl = "http://app.ontraport.com";
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester","passphrases are easy to break");
		Thread.sleep(3000);
		appUtilities.logOutOfApp(driver);
		Thread.sleep(3000);
		Assert.assertTrue("Log out succesfull-confirming by checking the existance of Login link", appUtilities.isElementPresent(driver, By.xpath("//div[@id='sod-drawer-handle']/div")));
		
	}

}
