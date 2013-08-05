package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteSingleContact extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	@Test
	public void testDeleteContact() throws Exception {
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		//login	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Contact']")).click();
		//Thread.sleep(3000);
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		System.out.println (varTimeStamp);
		WebElement fNameTxtBox = appUtilities.getTextBoxOnTheLabel (driver, "First Name");
		fNameTxtBox.clear();
		fNameTxtBox.sendKeys("Fname"+varTimeStamp);
		
		WebElement lNameTxtBox = appUtilities.getTextBoxOnTheLabel (driver, "Last Name");
		lNameTxtBox.clear();
		lNameTxtBox.sendKeys("lastname"+varTimeStamp);
		
		
		String emailId = "selenium"+varTimeStamp+"@test.com";
		WebElement emailTxtBox = appUtilities.getTextBoxOnTheLabel (driver, "E-Mail");
		emailTxtBox.clear();
		emailTxtBox.sendKeys(emailId);
		
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (emailId) +"']")));

		driver.findElement(By.xpath("//span[a[normalize-space(text())='"+emailId+"']]")).click();
		driver.findElement(By.linkText("Delete Contacts")).click();		
		driver.findElement(By.xpath("//*[@class='ussr-dialog-buttons']/button/span[normalize-space(text())='Ok']")).click();
		Thread.sleep (3000);
		AssertJUnit.assertFalse(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (emailId) +"']")));
		appUtilities.logOutOfApp(driver);
	}
	

}
