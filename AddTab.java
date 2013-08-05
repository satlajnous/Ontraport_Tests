package com.ontraport.app.selenium.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class AddTab extends OntraportFirefoxTest {

	AppUtilities appUtilities = new AppUtilities();
	
	
	@Test
	public void testAddTab () throws Exception{
		String tabName = "6test";
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		
		
		//Click Messages
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//li[@class='primary-nav-sub-item']//a//span[ text()='Settings']"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		driver.findElement(By.xpath("//li[@class='primary-nav-sub-item']//a//span[ text()='Settings']")).click();
		//Click Field Editor
		driver.findElement(By.xpath("//div[@class='ussr-list-item-desc' and text()='Add/Edit/Delete fields that make up your contact records.']")).click();
		Thread.sleep(6000);

		//Click "ADD TAB"
		driver.findElement(By.xpath("//div[@class='ussr-chrome-panel-pane-header-bottom ussr-theme-orange']//span[@class='ussr-icon ussr-icon-plus']")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Untitled')])[2]")).click();
		driver.findElement(By.cssSelector("input.ussr-widget-editinplace-input.text-transform-uppercase")).clear();
		driver.findElement(By.cssSelector("input.ussr-widget-editinplace-input.text-transform-uppercase")).sendKeys(tabName);
		
		//Click Save
		driver.findElement(By.xpath("//div[@id='ussr-chrome-panel-pane']//button/span[text()='Save']")).click();
		
		
		
		//Click Messages
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//li[@class='primary-nav-sub-item']//a//span[ text()='Settings']"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		//Click Field Editor
		driver.findElement(By.xpath("//div[@class='ussr-list-item-desc' and text()='Add/Edit/Delete fields that make up your contact records.']")).click();
		Thread.sleep(6000);
		driver.findElement(By.cssSelector("a.jb-overflowmenu-menu-secondary-handle > span.ussr-icon.ussr-icon-carat-2-s")).click();
		assertTrue(isElementPresent(By.xpath("(//a[contains(text(),'"+tabName+"')])[2]")));

		
		
		

		
		
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	
}
