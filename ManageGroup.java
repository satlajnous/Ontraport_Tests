package com.ontraport.app.selenium.tests;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class ManageGroup extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();

	@Test
	public void testMakeATagAndAddItToContact() throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		//long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String groupName = "SelGroup";
		
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='Manage Groups']")).click();
		appUtilities.selectItem(driver, "Select Permission...", "Everyone can view & Edit");
		driver.findElement(By.xpath("//input[@type='text' and normalize-space(@placeholder)='Enter Group Name...']")).sendKeys(groupName);
		appUtilities.selectItem(driver, "Select Field...", "Last Name");
		appUtilities.selectItem(driver, "Select Condition...", "contains");
		Thread.sleep (3000);
		driver.findElement(By.xpath("//input[@type='text' and normalize-space(@placeholder)='Enter Value...']")).sendKeys("Sel");
		driver.findElement(By.xpath("//button[@value='Save']")).click();
		Thread.sleep(5000);
		System.out.println("value:"+driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));

		org.junit.Assert.assertEquals(groupName, driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));
		appUtilities.logOutOfApp(driver);
	
		
		
	}

}
