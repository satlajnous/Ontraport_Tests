package com.ontraport.app.selenium.tests;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class MakeANewLP extends OntraportFirefoxTest{
	AppUtilities appUtilities = new AppUtilities();
		
	@Test
	public void testMakeANewLP () throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		appUtilities.navigateTo (driver, "Sites==Landing Page");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[normalize-space(text())='New Landing Page']")).click();
		driver.findElement(By.xpath("//div[div[*/text()='Easy Pages']]/descendant::button[*[normalize-space(text())='Create']]")).click();
		
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("SelLP"+varTimeStamp);
		
		driver.findElement(By.xpath("//div[@class='menu_button_class']//td[normalize-space(text())='Page URL:']")).click();
		//select radio button
		//driver.findElement(By.xpath("//*[*[normalize-space(text())='Use a Hosted Domain']]/descendant::input")).click();
		fillThePopUp (driver, "Use a Hosted Domain", "seleniumlandingpage");
		Thread.sleep (5000);
		driver.findElement(By.cssSelector("input.btn2")).click();
		//driver.findElement(By.xpath("//div[@class='ontraport_components_dialog']//input[@value='Accept']")).click();
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + ("SelLP"+varTimeStamp) +"']")));
	}
	
	
	
	public void fillThePopUp (WebDriver driver, String domaintType, String domainName){
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='ontraport_components_dialog']//div[@class='blue_box']"));
		Iterator<WebElement> itr = findElements.iterator();
		System.out.println("size:"+findElements.size());
		while (itr.hasNext()) {
			System.out.println("===============================================");
			WebElement mainDivEle = (WebElement) itr.next();
			System.out.println(mainDivEle.getText());
			if (mainDivEle.getText().toLowerCase().contains(domaintType.toLowerCase())){
				mainDivEle.findElement(By.xpath(".//input[@class='inpt_radio standard_input']")).click();
				mainDivEle.findElement(By.xpath(".//input[@class='inpt_text standard_input']")).sendKeys(domainName);
				break;
			}
			
		}
		
	}
	
}
