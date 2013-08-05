package com.ontraport.app.selenium.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteSendFromAddress extends OntraportFirefoxTest {
	
AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testDeleteSendFromAddress() throws InterruptedException{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		//long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String emailId = "DelSendEmail@email.com";
		
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//ul[@class='ussr-corner-bl']/li/a[text()='Admin']")).click();
		
		driver.findElement(By.xpath("//div[div[text()='E-Mail']]/descendant::a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[span[text()='Add E-Mail']]")).click();

		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(emailId);

		driver.findElement(By.xpath("//div[@class='ussr-dialog-buttons']//button/span[text()='Add E-Mail']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(emailId));
		driver.findElement(By.xpath("//tr[td[text()='"+emailId+"']]/descendant::td/span[text()='Remove']")).click();
		Thread.sleep(3000);
		Assert.assertFalse(driver.getPageSource().contains(emailId));
		appUtilities.logOutOfApp(driver);
		
	}

}
