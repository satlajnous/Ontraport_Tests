package com.ontraport.app.selenium.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class MakeATagAndAddItToContact extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();

	@Test
	public void testMakeATagAndAddItToContact() throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		//long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String tagName = "SelAddTag";
		Thread.sleep (5000);
		String contactName = appUtilities.selectNonBlankLastNameContactLink(driver);
		System.out.println("contactName:"+contactName);
		
		driver.findElement(By.linkText("Add/Remove Tags")).click();
		appUtilities.selectItemSpan(driver, "Select Tag", "Create New Tag");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.ussr-border-solid-all")).clear();
		driver.findElement(By.cssSelector("input.ussr-border-solid-all")).sendKeys(tagName);
		
		driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='Select Tag']]/descendant::button/span[normalize-space(text())='Save']")).click();
		driver.findElement(By.xpath("//button/span[normalize-space(text())='Submit']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//a[normalize-space(text())='" + contactName + "']")).click();
		
		driver.findElement(By.linkText("Sequences and Tags")).click();
		String xpathOfTag = "//div[label[normalize-space(text())='Contact Tags']]/descendant::li[normalize-space(text())='" + tagName + "']";
		Assert.assertTrue("Checking for tag added in Sequences and Tags", appUtilities.isElementPresent(driver, By.xpath(xpathOfTag)));
		
		appUtilities.logOutOfApp(driver);
		
		
		

		
	}	
}
