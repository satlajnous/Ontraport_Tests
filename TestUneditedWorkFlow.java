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

public class TestUneditedWorkFlow extends OntraportFirefoxTest{
	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testUneditedWorkflow ()throws Exception {
		driver.get(baseUrl + "/");
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		long varTimeStamp = Calendar.getInstance().getTimeInMillis();
		String gateWayName = "SelGW"+varTimeStamp;
		Thread.sleep(5000);
		clickOnNonBlankLastNameContactLink(driver);
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='Actions']")).click();
		driver.findElement(By.linkText("Log Transaction")).click();
				
		Thread.sleep(3000);
		appUtilities.selectItemSpan(driver, "Select Gateway...", "Create New Gateway");
		driver.findElement(By.xpath("//input[@placeholder='Enter Name']")).sendKeys(gateWayName);

		driver.findElement(By.xpath("//button/span[text()='Save as Draft']")).click();
		driver.findElement(By.xpath("//button[@value='Cancel']")).click();

		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span.ussr-icon.ussr-icon-circle-file")).click();
		Thread.sleep(5000);
		
		boolean b = isUnEditItemsLogAvaialable(gateWayName);
		System.out.println(b);
		AssertJUnit.assertEquals(true, b);
		
		
	}
	
	public void clickOnNonBlankLastNameContactLink (WebDriver driver){
		List<WebElement> findElements = driver.findElements(By.xpath("//table[@class='ussr-table-striped']//td[2]/span/a"));
		Iterator<WebElement> iterator = findElements.iterator();
		while (iterator.hasNext()) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println("linkname:"+webElement.getText());
			System.out.println(webElement.getText().trim().length());
			System.out.println("");
			if (webElement.getText().trim().length()!=0){
				webElement.click();
				return;
			}
			
		}
	}
	
	public boolean isUnEditItemsLogAvaialable(String gatewayname) throws InterruptedException{
		String nxtBtnXpath = "//div[table[contains(@class,'ussr-workflow-menu-section-list-type-unedited')]]/descendant::a[@class='ussr-paginator-control-next']/span";
		String disabledNxtBtnXpath = "//div[table[contains(@class,'ussr-workflow-menu-section-list-type-unedited')]]/descendant::a[@class='ussr-paginator-control-next']/span[@class='ussr-icon ussr-icon-seek-next ussr-state-disabled']";
		//String logLinkXpath = "//table[contains(@class,'ussr-workflow-menu-section-list-type-unedited')]//a[text()='Gateway: "+gatewayname+"']";
		String gtName = "Gateway: "+gatewayname;
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
		List<WebElement> findElements = driver.findElements(By.xpath("//table[contains(@class,'ussr-workflow-menu-section-list-type-unedited')]//a"));
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
