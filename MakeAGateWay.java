package com.ontraport.app.selenium.tests;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class MakeAGateWay extends OntraportFirefoxTest {

	@Test
	public void testCreateContact() throws Exception {
		//baseUrl = "http://app.ontraport.com";
		driver.get(baseUrl + "/");
		loginToApp();
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		driver.findElement(By.xpath("//*[@class='primary-nav-item-label' and text()='Sales']")).click();
		driver.findElement(By.xpath("//li[3]//*[@class='primary-nav-sub-item']/a//span[text()='Settings']")).click();
		
		//Clicking checkbox and navigating to New Gateway page
		driver.findElement(By.xpath("//span[@class='ussr-icon ussr-icon-textfield']")).click();
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Gateway']")).click();
		
		//Typing name
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("SelGW"+varTimeStamp);
		
		//Selecting from drop down
		driver.findElement(By.xpath("//div[@class='ussr-gateway-editor']//button")).click();
		driver.findElement(By.xpath("//div//ul[@class='ussr-component-drilldownselect-ul']/li/div[normalize-space(text())='Dummy Gateway']")).click();

		//Fill the nickname field
		WebElement nicknameTxtBox = getTextBoxOnTheLabel(driver, "nickname");
		nicknameTxtBox.clear();
		nicknameTxtBox.sendKeys("nick"+varTimeStamp);
		
		
		
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		
		AssertJUnit.assertTrue(isElementPresent(By.xpath("//a[normalize-space(text())='" + ("SelGW"+varTimeStamp) +"']")));

		
		//Logout
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user"))
				.click();
		driver.findElement(By.cssSelector("a[href=\"Login/logout\"]")).click();
		
	}




	public void loginToApp (){
		driver.findElement(By.xpath("//div[@id='sod-drawer-handle']/div")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("tester");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("passphrases are easy to break");
		driver.findElement(By.cssSelector("input.submit")).click();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//li[@class='primary-nav-sub-item']//a//span[ text()='Messages']"))) break; } catch (Exception e) {}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public WebElement getTextBoxOnTheLabel (WebDriver driver, String textboxLabel) throws Exception{
		List<WebElement> textBoxParentElement = driver.findElements(By.xpath("//*[@class='ussr-component-input ussr-form-input-type-text  clearfix']"));
		Iterator<WebElement> itr = textBoxParentElement.iterator();
		while (itr.hasNext()) {
			WebElement eachParentElement = (WebElement) itr.next();
			String textfromApp = eachParentElement.getText().trim();
			System.out.println (textfromApp);
			if (textfromApp.equalsIgnoreCase(textboxLabel)){
				return eachParentElement.findElement(By.xpath(".//input[@type='text']"));
			}
			
		}
		
		throw new Exception ("No Label Match found");
		
	}
}
