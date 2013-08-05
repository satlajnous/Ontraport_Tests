package com.ontraport.app.selenium.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class SetTimezone extends OntraportFirefoxTest{
	AppUtilities appUtilities = new AppUtilities();
	@Test
	public void testSetTimezone () throws Exception{
		driver.get(baseUrl + "/");
		String timezone = "HI";
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");

		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.linkText("Personal Settings")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li/a[text()='Password & Misc.']")).click();
		Thread.sleep(3000);
		String existingTimeZone = getTimeZoneFromApp(driver);
		setTimeZone (driver, timezone);
		driver.findElement(By.xpath("//span[@class='primary-nav-item-label' and normalize-space(text())='Contacts']")).click();
		
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.linkText("Personal Settings")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li/a[text()='Password & Misc.']")).click();
		Thread.sleep(3000);
		String timezoneFromApp = driver.findElement(By.xpath("//div[label[text()='Timezone']]//span")).getText().trim();
		Assert.assertTrue(timezoneFromApp.equalsIgnoreCase(timezone));
		setTimeZone (driver, existingTimeZone);
		appUtilities.logOutOfApp(driver);
		
	}
	
	public void setTimeZone (WebDriver driver, String timezone) throws InterruptedException{
		driver.findElement(By.xpath("//div[label[text()='Timezone']]//span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li/div[text()='"+timezone+"']")).click();
	}
	
	public String getTimeZoneFromApp (WebDriver driver){
		return driver.findElement(By.xpath("//div[label[text()='Timezone']]//span")).getText().trim();
	}

}
