package com.ontraport.app.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class ChangeUserInfo extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	@Test
	public void testChangeUserInfo() throws Exception {
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester","passphrases are easy to break");
		String FirstName = "testFirstName";
		String LastName = "testLastName";
		String Telephone = "testTelephone";
		String CellPhone = "testCellPhone";
		String Fax = "testFax";
		
		//Navigate to Personal Settings
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//div[@id='ussr-chrome-wrapper']//*[@class='ussr-corner-bl']//a[normalize-space(text())='Personal Settings']")).click();
		
		//Change contact information
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='First Name']/parent::div/div")).click();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='First Name']/parent::div//div/input")).clear();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='First Name']/parent::div//div/input")).sendKeys(FirstName);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Last Name']/parent::div/div")).click();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Last Name']/parent::div//div/input")).clear();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Last Name']/parent::div//div/input")).sendKeys(LastName);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Telephone']/parent::div/div")).click();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Telephone']/parent::div//div/input")).clear();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Telephone']/parent::div//div/input")).sendKeys(Telephone);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Cell Phone']/parent::div/div")).click();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Cell Phone']/parent::div//div/input")).clear();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Cell Phone']/parent::div//div/input")).sendKeys(CellPhone);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Fax']/parent::div/div")).click();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Fax']/parent::div//div/input")).clear();
		driver.findElement(By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Fax']/parent::div//div/input")).sendKeys(Fax);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space(text())='Save']")).click();

		//Navigate to Contacts
		
		driver.findElement(By.xpath("//span[@class='primary-nav-item-label' and normalize-space(text())='Contacts']")).click();
		Thread.sleep(5000);
		//Navigate to Personal Settings
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.xpath("//div[@id='ussr-chrome-wrapper']//*[@class='ussr-corner-bl']//a[normalize-space(text())='Personal Settings']")).click();
		
		//Check for changed values
		
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='First Name']/parent::div//div[normalize-space(text())='"+FirstName+"']")));
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Last Name']/parent::div//div[normalize-space(text())='"+LastName+"']")));
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Telephone']/parent::div//div[normalize-space(text())='"+Telephone+"']")));
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Cell Phone']/parent::div//div[normalize-space(text())='"+CellPhone+"']")));
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//label[@class='ussr-form-label' and normalize-space(text())='Fax']/parent::div//div[normalize-space(text())='"+Fax+"']")));
		appUtilities.logOutOfApp(driver);
	}

}
