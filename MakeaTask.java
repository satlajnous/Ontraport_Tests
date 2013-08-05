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

	public class MakeaTask extends OntraportFirefoxTest {
		
	AppUtilities appUtilities = new AppUtilities();	
	
		@Test

		public void testMakeaTask() throws Exception {

			driver.get(baseUrl + "/");

			//login

			appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
			String taskName = "SelRule"+Calendar.getInstance().getTimeInMillis();
			driver.findElement(By.xpath("//*[@class='primary-nav-sub-item']/a//span[text()='Messages']")).click();
			driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='New Message']")).click();
			driver.findElement(By.xpath("//div[div[*/text()='Task']]/descendant::button[*[normalize-space(text())='Create']]")).click();
			Thread.sleep (3000);
			driver.findElement(By.xpath("//input[@type='text']")).clear();
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(taskName);
			
			getTextBoxOnTheLabel(driver, "Task Subject").sendKeys("You need to do this!");
			getTextBoxOnTheLabel(driver, "Due Date").sendKeys("1");
			
			selectItem("Select User", "Pin Chen");

			
			selectItem("Insert Merge Field", "First Name");
			selectItem("Insert Merge Field", "First Name");
			
			
			driver.findElement(By.xpath("//*[@class='task_notification_control']//span")).click();
			
			//driver.findElement(By.xpath("//div[div[*/text()='Select...']]/descendant::button[*[normalize-space(text())='Contact Owner']]")).click();
			fillTaskOwnerNotification (driver);
			driver.findElement(By.xpath("//div[text()='Add New']")).click();
			Thread.sleep(4000);
			
			selectItem("Select OUTCOME NAME...", "Create New Outcome");
			
			driver.findElement(By.xpath("(//input[@type='text'])[11]")).click();
			driver.findElement(By.xpath("(//input[@type='text'])[11]")).clear();
			driver.findElement(By.xpath("(//input[@type='text'])[11]")).sendKeys("Selenium Outcome");
			driver.findElement(By.xpath("//div[@id='ussr-chrome-panel-pane']/div[4]/div/div/div/div[3]/div/div[2]/table/tbody/tr[2]/td[2]/div/div/div/div")).click();

			selectItem("Select Action...", "Recharge all declined transactions");
			driver.findElement(By.xpath("//div[@id='ussr-chrome-panel-pane']/div[4]/div/div/div/div[3]/div/div[2]/table/tbody/tr[2]/td[2]/div/div/div[3]/span[2]")).click();

			driver.findElement(By.xpath("//button//span[text()='Save']")).click();
			
			AssertJUnit.assertTrue(appUtilities.isElementPresent(driver, By.xpath("//a[normalize-space(text())='" + (taskName) +"']")));
			/*selectItem("Select...", "Contact Owner");
			
			driver.findElement(By.xpath("(//input[@type='text'])[7]")).clear();
			driver.findElement(By.xpath("(//input[@type='text'])[7]")).sendKeys("1");*/
			
			

			

			


		}	
			
		
		public void selectItem(String itemLink, String itemName ){
			
			
			try {
				driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='"+ (itemLink) +"']]/descendant::button")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//div[input[@placeholder='"+ (itemLink) +"']]/descendant::li/div[text()='"+ (itemName) +"']")).click();
			} catch (InterruptedException e) {

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
		
		
		public void fillTaskOwnerNotification (WebDriver driver){
			String[] str = {"Contact Owner", "After Task is Assigned"} ;
			List<WebElement> childElement = driver.findElements(By.xpath("//div[input[normalize-space(@placeholder)='Select...']]"));
			Iterator< WebElement> childItr = childElement.iterator();
			for (int i = 0; i < childElement.size()-1; i++) {
				WebElement ele = childItr.next();
				ele.findElement(By.xpath(".//button")).click();
				driver.findElement(By.xpath("//li/div[text()='"+str[i] + "']")).click();
				//li/div[text()='After Task is Assigned']
				
			}
			
			driver.findElement(By.xpath("(//input[@type='text'])[7]")).sendKeys("1");
			
		}
		
		
		
	}
	