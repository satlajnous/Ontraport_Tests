package com.ontraport.app.selenium.tests;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class AddNewSection extends OntraportFirefoxTest {

	AppUtilities appUtilities = new AppUtilities();
	

	@Test
	public void testAddNewSection() throws Exception {

		driver.get(baseUrl + "/");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String sectionTitle = "SelTitle"+varTimeStamp;
		String sectionDesc = "SelDesc"+varTimeStamp;
		
		System.out.println(sectionTitle);
		System.out.println(sectionDesc);
		
		// login

		appUtilities.loginToApp(driver, "tester","passphrases are easy to break");

		//Click Settings
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//li[@class='primary-nav-sub-item']//a//span[ text()='Settings']"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		driver.findElement(By.xpath("//li[@class='primary-nav-sub-item']//a//span[ text()='Settings']")).click();
		Thread.sleep(3000);
	
		//Click Field Editor
		driver.findElement(By.xpath("//div[@class='ussr-list-item-desc' and text()='Add/Edit/Delete fields that make up your contact records.']")).click();
		Thread.sleep(10000);
		
		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = driver.findElement(By.xpath("//div[@class='jb-ace-scroll-wrapper jb-ace-scroll jb-ace-scroll-orientation-vertical']//div[@class='jb-ace-scroll-scrollbar-middle']"));
		int numberOfPixelsToDragTheScrollbarDown = 100;

	    for (int i=10;i<1000;i=i+numberOfPixelsToDragTheScrollbarDown){
	        try{    		
	    		        	
	    dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	    Thread.sleep(2000);
	        }catch(Exception e1){}
	    } 
	    Thread.sleep(5000);
	    
	    //Click ADD NEW SECTION
	    driver.findElement(By.xpath("//div[@class='ussr-pane-field-editor-new-section']//span[normalize-space(text())='add new section']")).click();
	    Thread.sleep(5000);
	    
	    for (int i=5;i<100;i=i+numberOfPixelsToDragTheScrollbarDown){
	        try{
	    		
	    		//try { if (isElementPresent(By.xpath("//div[@class='ussr-pane-field-editor-new-section']//span[normalize-space(text())='add new section']"))) break; } catch (Exception e) {}
	        	
	    dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	    Thread.sleep(2000);
	        }catch(Exception e1){}
	    } 
		
    
	    Thread.sleep(6000);
	    driver.findElement(By.xpath("//div[@class='ussr-component-section-titlebar ussr-border-solid-bottom']//span[normalize-space(text())='Untitled']")).click();
	    driver.findElement(By.xpath("//div[*[text()='Section Description']]/descendant::input")).clear();
	    driver.findElement(By.xpath("//div[*[text()='Section Description']]/descendant::input")).sendKeys(sectionTitle);

	    Thread.sleep(6000);
	    driver.findElement(By.xpath("//div//div[normalize-space(text())='Section Description']")).click();
	    driver.findElement(By.cssSelector("input.ussr-widget-editinplace-input")).clear();
	    driver.findElement(By.cssSelector("input.ussr-widget-editinplace-input")).sendKeys(sectionDesc);



	    
		//Click Save
		driver.findElement(By.xpath("//div[@id='ussr-chrome-panel-pane']//button/span[text()='Save']")).click();

		//Click Field Editor
		driver.findElement(By.xpath("//div[@class='ussr-list-item-desc' and text()='Add/Edit/Delete fields that make up your contact records.']")).click();
		Thread.sleep(6000);
		
		
		Thread.sleep(6000);
		draggablePartOfScrollbar = driver.findElement(By.xpath("//div[@class='jb-ace-scroll-wrapper jb-ace-scroll jb-ace-scroll-orientation-vertical']//div[@class='jb-ace-scroll-scrollbar-middle']"));
	    for (int i=10;i<500;i=i+numberOfPixelsToDragTheScrollbarDown){
	        try{
	    		
	    		//try { if (isElementPresent(By.xpath("//div[@class='ussr-pane-field-editor-new-section']//span[normalize-space(text())='add new section']"))) break; } catch (Exception e) {}
	        	
	    dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	    Thread.sleep(2000);
	        }catch(Exception e1){}
	    } 
		//AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//div[@class='ussr-component-section-titlebar ussr-border-solid-bottom']//span[@class='ussr-component-section-title big ussr-helper-text-transform-uppercase' and normalize-space(text())='"+sectionTitle+"']")));
		//AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//div[@class='ussr-component-section-description ussr-widget-editinplace' and normalize-space(text())='"+sectionDesc+"']")));
	    
	    AssertJUnit.assertTrue(driver.getPageSource().contains(sectionTitle));
	    AssertJUnit.assertTrue(driver.getPageSource().contains(sectionDesc));
			
	
		

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