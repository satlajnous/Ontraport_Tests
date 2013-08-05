package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class MakeAdditionalUser extends OntraportFirefoxTest {
AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testMakeAdditionalUser() throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String emailId = "SelUsr"+varTimeStamp+"@gmail.com";
		
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//ul[@class='ussr-corner-bl']/li/a[text()='Manage Users']")).click();
		
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New User']")).click();
		Thread.sleep (3000);
		driver.findElement(By.xpath("//*[@class='ussr-dialog-buttons']/button/span[normalize-space(text())='Agree']")).click();
		
		appUtilities.getTextBoxOnTheLabel(driver, "First Name").sendKeys("Selenium");
		appUtilities.getTextBoxOnTheLabel(driver, "Last Name").sendKeys("Test");
		appUtilities.getTextBoxOnTheLabel(driver, "Email 'From' Name").sendKeys("Selenium");
		appUtilities.getTextBoxOnTheLabel(driver, "Reply-To Email").sendKeys(emailId);
		Thread.sleep (3000);
		//driver.findElement(By.partialLinkText("Password & Misc")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Password & Misc.')]")).click();

		appUtilities.getTextBoxOnTheLabel(driver, "New Password").sendKeys("test");
		appUtilities.getTextBoxOnTheLabel(driver, "Password Confirm").sendKeys("test");
		
		appUtilities.selectItem(driver, "Select Role", "Sub Director");
		appUtilities.selectItem(driver, "Select User", "Pin Chen");
		appUtilities.selectItem(driver, "Select Timezone...", "EST");
		appUtilities.selectItem(driver, "Select Language...", "English");
		
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (emailId) +"']")));
	}
	
}
