package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class MakeAdditionalRole  extends OntraportFirefoxTest{
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testMakeARole(){
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String roleName = "Role"+varTimeStamp;
		
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//ul[@class='ussr-corner-bl']/li/a[text()='Admin']")).click();
		
		driver.findElement(By.xpath("//a/div[text()='Team Roles & Permission Management']")).click();
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Role']")).click();
		
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(roleName);

		selectItem ("Select Role", "Administrator");
		
		driver.findElement(By.xpath("//div[label[text()='Dashboard']]/descendant::a/span")).click();
		driver.findElement(By.xpath("//div[label[text()='Can Send Emails']]/descendant::a/span")).click();
		driver.findElement(By.xpath("//div[label[text()='Can Manage Sequences']]/descendant::a/span")).click();

		
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (roleName) +"']")));
		
		
		
	}
	
	public void selectItem(String itemLink, String itemName ){
		
		
		try {
			driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='"+ (itemLink) +"']]/descendant::button")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[input[@placeholder='"+ (itemLink) +"']]/descendant::li/div[text()='"+ (itemName) +"']")).click();
		} catch (InterruptedException e) {

		}
	}

}
