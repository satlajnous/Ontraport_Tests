package com.ontraport.app.selenium.tests;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class AddWeightedRandom extends OntraportFirefoxTest {

	AppUtilities appUtilities = new AppUtilities();
	

	@Test
	public void testAddRoundRobin() throws Exception {

		driver.get(baseUrl + "/");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String name = "SelTitle"+varTimeStamp;
		
		appUtilities.loginToApp(driver, "tester","passphrases are easy to break");
		Thread.sleep (5000);
		driver.findElement(By.xpath("//*[@class='primary-nav-sub-item']/a//span[text()='Settings']")).click();
		Thread.sleep (2000);
		driver.findElement(By.xpath("//div[@id='ussr-chrome-panel-pane']/div[4]/div/div/div/div/ul/li[3]/a/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Lead Router']")).click();
		Thread.sleep (2000);
		driver.findElement(By.xpath("//div[div[a[text()='Weighted Random']]]/descendant::a/button")).click();

		Thread.sleep (5000);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(name);
		
		//driver.findElement(By.xpath("//div/div[text()='Add User']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='target-rout ussr-component ussr-component-lead_rout_target ontraport_components_lead_rout_target'][1]//button")).click();
		Thread.sleep(2000);
		String s1 = driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='Select...']]/descendant::li[1]")).getText();
		driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='Select...']]/descendant::li[1]")).click();
		
		driver.findElement(By.xpath("//div[@class='lead_rout_entry_row']//div[@class='header_box target-break ussr-component ussr-component-form_control_input_text ontraport_components_form_control_input_text ussr-form-cell ussr-component-form-control clearfix']//input")).clear();
		driver.findElement(By.xpath("//div[@class='lead_rout_entry_row']//div[@class='header_box target-break ussr-component ussr-component-form_control_input_text ontraport_components_form_control_input_text ussr-form-cell ussr-component-form-control clearfix']//input")).sendKeys("50");
			
		driver.findElement(By.xpath("//div/div[text()='Add User']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='target-rout ussr-component ussr-component-lead_rout_target ontraport_components_lead_rout_target'][2]//button")).click();
		
		String s2 = driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='Select...']]/descendant::li[2]")).getText();
		driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='Select...']]/descendant::li[2]")).click();
		
		
		driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("50");
		
		driver.findElement(By.xpath("//button//span[text()='Save']")).click();
		Thread.sleep(6000);
		
		Assert.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (name) +"']")));
		driver.findElement(By.xpath("//a[normalize-space(text())='" + (name) +"']")).click();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='text'])[2]")).getAttribute("value").trim().equalsIgnoreCase(s1.trim()));
		Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='text'])[4]")).getAttribute("value").trim().equalsIgnoreCase(s2.trim()));
		
		
		
		appUtilities.logOutOfApp(driver);
		
		
	}

}
