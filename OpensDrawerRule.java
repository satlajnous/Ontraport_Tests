package com.ontraport.app.selenium.tests;



import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;


import com.ontraport.app.selenium.tools.OntraportFirefoxTest;

public class OpensDrawerRule extends OntraportFirefoxTest {

	AppUtilities appUtilities = new AppUtilities();
	
	@Test
	public void testOpensDrawerRule() throws Exception {
		
		driver.get(baseUrl + "/");
		//login
		
		appUtilities.loginToApp(driver, "tester", "passphrases are easy to break");
		
		//Click Rules
		driver.findElement(By.xpath("//*[@class='primary-nav-sub-item']/a//span[text()='Rules']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='panelbuttonbar']/div//span[text()='Actions']")).click();

		
		String actionTxt= driver.findElement(By.xpath("//div[@class='ussr-chrome-panel-action-drawer-content ussr-texture-flat-light']")).getText().trim();
		System.out.println(actionTxt);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ussr-chrome-panel-action-drawer-content ussr-texture-flat-light']")).isDisplayed());
		Assert.assertTrue(actionTxt.contains("Delete Rule"));
		Assert.assertTrue(actionTxt.contains("Unpause Rule"));
		Assert.assertTrue(actionTxt.contains("Pause Rule"));
		
		
		
		//Logout
		appUtilities.logOutOfApp(driver);
	}
	
	

	
	
	

}
