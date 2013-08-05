package com.ontraport.app.selenium.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class AddDeleteUserPermission extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	@Test
	public void testChangeUserInfo() throws Exception {
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester","passphrases are easy to break");
				
		//Navigate to Personal Settings
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//div[@id='ussr-chrome-wrapper']//*[@class='ussr-corner-bl']//a[normalize-space(text())='Personal Settings']")).click();
		driver.findElement(By.xpath("//a/span[text()='Actions']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[a[span[text()='Actions']]]/descendant::a/span[@class='ussr-icon ussr-icon-mail-closed']")).click();
		Thread.sleep(2000);
		appUtilities.selectItem(driver, "Choose an Permission Exception", "Can View Contacts");
		Thread.sleep(2000);
		appUtilities.selectItem(driver, "Select Can View Contacts...", "All)");
		
		driver.findElement(By.xpath("//span[text()='Add Exception']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//td/span/a[text()='Can View Contacts']")));
		
		driver.findElement(By.xpath("//span[@class='primary-nav-item-label' and normalize-space(text())='Contacts']")).click();
		Thread.sleep(5000);
		//Navigate to Personal Settings
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//div[@id='ussr-chrome-wrapper']//*[@class='ussr-corner-bl']//a[normalize-space(text())='Personal Settings']")).click();
		Thread.sleep(3000);
		//Check for changed values
		Assert.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//td/span/a[text()='Can View Contacts']")));
		
		driver.findElement(By.xpath("//a/span[text()='Actions']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[a[span[text()='Actions']]]/descendant::a/span[@class='ussr-icon ussr-icon-trashcan']")).click();
		
		driver.findElement(By.xpath("//*[@class='ussr-dialog-buttons']/button/span[normalize-space(text())='Ok']")).click();
		Thread.sleep(3000);
		Assert.assertFalse(appUtilities.isElementPresent(driver, By.xpath("//td/span/a[text()='Can View Contacts']")));
		String txt = driver.findElement(By.xpath("//div[span[@class='ussr-state-empty']]")).getText();
		System.out.println ("txt:"+txt);
		Assert.assertTrue(txt.trim().equalsIgnoreCase("There are no items to display"));
		
	}

}
