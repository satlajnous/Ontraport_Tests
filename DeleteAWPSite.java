package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteAWPSite extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	@Test
	public void testDeleteAWPSite() throws Exception {
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester","passphrases are easy to break");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String siteName = "SelSite"+varTimeStamp;
		String domain = "seleniumwp2"+varTimeStamp;
		String level = "Level 1";
		
		//Navigate to Word Press page
		appUtilities.navigateTo(driver, "Sites==WordPress");
		
		
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[normalize-space(text())='New Website']")).click();
		driver.findElement(By.xpath("//div[div[*/text()='New Wordpress Site']]/descendant::button[*[normalize-space(text())='Create']]")).click();
		Thread.sleep(5000);
		
		// Enter WebSite Name
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(siteName);
		
		//Enter Domain
		driver.findElement(By.xpath("//input[@value='ours']")).click();
		driver.findElement(By.xpath("//div[div[*/text()='http://']]/descendant::input")).click();
		driver.findElement(By.xpath("//div[div[*/text()='http://']]/descendant::input")).clear();
		driver.findElement(By.xpath("//div[div[*/text()='http://']]/descendant::input")).sendKeys(domain);
		
		//Enter Membership Level
		driver.findElement(By.xpath("//div[div[*/text()='Add membership level']]/descendant::input")).click();
		driver.findElement(By.xpath("//div[div[*/text()='Add membership level']]/descendant::input")).clear();
		driver.findElement(By.xpath("//div[div[*/text()='Add membership level']]/descendant::input")).sendKeys(level);
		driver.findElement(By.xpath("//span[normalize-space(text())='Add']")).click();
		
		driver.findElement(By.xpath("//div[@id='ussr-chrome-panel-pane']//button/span[text()='Save']")).click();
		Thread.sleep(8000);		
		driver.findElement(By.xpath("//div[@class='ussr-dialog-buttons']/button")).click();

		Thread.sleep(8000);
		
		Assert.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" +(siteName)+"']")));

		//Delete the Website
		driver.findElement(By.xpath("//a[normalize-space(text())='"+(siteName)+"']/parent::span")).click();
		driver.findElement(By.linkText("Delete")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='ussr-dialog-buttons']//span[normalize-space(text())='Ok']/parent::button")).click();
		Thread.sleep(3000);
		Assert.assertFalse(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" +(siteName)+"']")));
		appUtilities.logOutOfApp(driver);
		
		
		

		

		


		
		
		
		
		
	}
	

}
