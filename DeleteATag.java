package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteATag extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testCreateATag() throws InterruptedException{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		//long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String tagName = "SelTagDel";
		
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//ul[@class='ussr-corner-bl']/li/a[text()='Admin']")).click();
		
		
		
		driver.findElement(By.xpath("//a/div[text()='MANAGE TAGS']")).click();
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Tag']")).click();
		
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(tagName);
	
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		Thread.sleep(7000);
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//*[normalize-space(text())='" + (tagName) +"']")));
		
		Thread.sleep(3000);
		WebElement chkBox = driver.findElement(By.xpath("//tr[td[span[normalize-space(text())='" + tagName + "']]]/descendant::td[3]"));
		chkBox.click();
		Thread.sleep (2000);
		driver.findElement(By.linkText("Delete Tag")).click();		
		driver.findElement(By.xpath("//*[@class='ussr-dialog-buttons']/button/span[normalize-space(text())='Ok']")).click();
		Thread.sleep (3000);
		Assert.assertFalse(appUtilities.isElementPresent(driver, By.linkText(tagName)));
		appUtilities.logOutOfApp(driver);
		
		
	}

}
