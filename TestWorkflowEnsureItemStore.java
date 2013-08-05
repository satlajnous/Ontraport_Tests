package com.ontraport.app.selenium.tests;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class TestWorkflowEnsureItemStore extends OntraportFirefoxTest{
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testWorkflowEnsureItemStore()throws Exception{
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		Thread.sleep (5000);
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String name = "Sel"+varTimeStamp;
		System.out.println (varTimeStamp);
		
		appUtilities.selectNonBlankLastNameContactLink(driver);
		Thread.sleep (5000);
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='Actions']")).click();
		Thread.sleep (3000);
		driver.findElement(By.linkText("Send E-Mail")).click();
		appUtilities.selectItemBasedOnIndex(driver, "Select e-mail", 1);
		Thread.sleep (5000);
		driver.findElement(By.cssSelector("input.ussr-border-solid-all")).clear();
		driver.findElement(By.cssSelector("input.ussr-border-solid-all")).sendKeys(name);
		driver.findElement(By.xpath("//button/span[text()='Save and Edit']")).click();
		Thread.sleep (5000);
		driver.findElement(By.xpath("//button//span[text()='Cancel']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span.ussr-icon.ussr-icon-circle-file")).click();
		Thread.sleep(5000);
		
		Assert.assertTrue(isUnEditItemsLogAvaialable(name));
		
		driver.findElement(By.cssSelector("a.ussr-icon.ussr-icon-circle-file")).click();
		Thread.sleep(5000);


		
		
	}
	
	
	
	public boolean isUnEditItemsLogAvaialable(String gatewayname) throws InterruptedException{
		String nxtBtnXpath = "//div[table[contains(@class,'ussr-workflow-menu-section-list-type-work-flow')]]/descendant::a[@class='ussr-paginator-control-next']/span";
		//String disabledNxtBtnXpath = "//div[table[contains(@class,'ussr-workflow-menu-section-list-type-work-flow')]]/descendant::a[@class='ussr-paginator-control-next']/span[@class='ussr-icon ussr-icon-seek-next ussr-state-disabled']";
		//String logLinkXpath = "//table[contains(@class,'ussr-workflow-menu-section-list-type-unedited')]//a[text()='Gateway: "+gatewayname+"']";
		String gtName = "Editing Message: "+gatewayname;
		int flag = 0;
		do {
			if (isLinkPresentIntable(driver, gtName)){
				return true;
			}else{	
				if (appUtilities.isElementPresent(driver, By.xpath(nxtBtnXpath))){
					driver.findElement(By.xpath(nxtBtnXpath)).click();
					Thread.sleep(3000);
					flag=1;
				}else{
					return false;
				}
			}
			
		} while (flag==1);
		return false;
		
		
	}
	
	
	public boolean isLinkPresentIntable (WebDriver driver, String gateWayName){
		//"ussr-workflow-menu-section ussr-helper-float-left"
		List<WebElement> findElements = driver.findElements(By.xpath("//table[contains(@class,'ussr-workflow-menu-section-list-type-work-flow')]//a"));
		Iterator<WebElement> iterator = findElements.iterator();
		while (iterator.hasNext()) {
			WebElement webElement = (WebElement) iterator.next();
			String linkText = webElement.getText().trim();
			System.out.println("linkname:"+linkText);
			System.out.println(webElement.getText().trim().length());
			if (linkText.equalsIgnoreCase(gateWayName.trim())){
				return true;
			}
			
		}
		
		return false;
	}
	
	
}
