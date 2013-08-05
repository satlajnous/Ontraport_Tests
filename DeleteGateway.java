package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class DeleteGateway extends OntraportFirefoxTest {
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testDeleteGateWay() throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String gatewayName = "SelGW" + varTimeStamp;
		
		driver.findElement(By.xpath("//*[@class='primary-nav-item-label' and text()='Sales']")).click();
		driver.findElement(By.xpath("//li[3]//*[@class='primary-nav-sub-item']/a//span[text()='Settings']")).click();
		
		//Clicking checkbox and navigating to New Gateway page
		driver.findElement(By.xpath("//span[@class='ussr-icon ussr-icon-textfield']")).click();
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Gateway']")).click();
		
		//Typing name
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(gatewayName);
		
		//Selecting from drop down
		driver.findElement(By.xpath("//div[@class='ussr-gateway-editor']//button")).click();
		driver.findElement(By.xpath("//div//ul[@class='ussr-component-drilldownselect-ul']/li/div[normalize-space(text())='Dummy Gateway']")).click();

		//Fill the nickname field
		WebElement nicknameTxtBox = appUtilities.getTextBoxOnTheLabel(driver, "nickname");
		nicknameTxtBox.clear();
		nicknameTxtBox.sendKeys("nick"+varTimeStamp);
		
		
		
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		
		Assert.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (gatewayName) +"']")));
		Thread.sleep(3000);
		WebElement chkBox = driver.findElement(By.xpath("//tr[td[span[a[normalize-space(text())='" + gatewayName + "']]]]/descendant::td[3]"));
		chkBox.click();
		Thread.sleep (2000);
		driver.findElement(By.linkText("Delete Gateway")).click();		
		driver.findElement(By.xpath("//*[@class='ussr-dialog-buttons']/button/span[normalize-space(text())='Ok']")).click();
		Thread.sleep (3000);
		Assert.assertFalse(appUtilities.isElementPresent(driver, By.linkText(gatewayName)));
		appUtilities.logOutOfApp(driver);

		
	}

}
