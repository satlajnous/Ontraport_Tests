package com.ontraport.app.selenium.tests;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.Assert.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;

public class AppUtilities {
	
	public boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void loginToApp (WebDriver driver, String userName, String password){
		driver.findElement(By.xpath("//div[@id='sod-drawer-handle']/div")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.submit")).click();
		waitForElement(driver, "//li[@class='primary-nav-sub-item']//a//span[ text()='Messages']", 10);
	}
	
	public void waitForElement (WebDriver driver, String sXpath, int timeInSeconds){
		for (int second = 0;; second++) {
			if (second >= timeInSeconds) fail("timeout");
			try {
				if (isElementPresent(driver, By.xpath(sXpath)))
					break; 
				} catch (Exception e) {}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void logOutOfApp (WebDriver driver){
		driver.findElement(By.cssSelector("li.ussr-header-nav-option-user")).click();
		driver.findElement(By.cssSelector("a[href=\"Login/logout\"]")).click();
	}
	
	public WebElement getTextBoxOnTheLabel (WebDriver driver, String textboxLabel, String parentXpath, String childXpath) throws Exception{
		//List<WebElement> textBoxParentElement = driver.findElements(By.xpath("//*[@class='ussr-component-input ussr-form-input-type-text  clearfix']"));
		List<WebElement> textBoxParentElement = driver.findElements(By.xpath(parentXpath));
		Iterator<WebElement> itr = textBoxParentElement.iterator();
		while (itr.hasNext()) {
			WebElement eachParentElement = (WebElement) itr.next();
			String textfromApp = eachParentElement.getText().trim();
			System.out.println (textfromApp);
			if (textfromApp.equalsIgnoreCase(textboxLabel)){
				//return eachParentElement.findElement(By.xpath(".//input[@type='text']"));
				return eachParentElement.findElement(By.xpath(childXpath));
			}
			
		}
		
		throw new Exception ("No Label Match found");
		
	}
	
	public WebElement getTextBoxOnTheLabel (WebDriver driver, String textboxLabel) throws Exception{
		return getTextBoxOnTheLabel(driver, textboxLabel, 
				"//*[@class='ussr-component-input ussr-form-input-type-text  clearfix']", ".//input[@type='text']");
		
	}
	
	/**
	 * Navigate to the side menu
	 * @param driver
	 * @param menuPath primary==secondary
	 * @throws Exception
	 */
	public void navigateTo (WebDriver driver, String menuPath) throws Exception{
		String[] menus = menuPath.split("==");
		waitForElement(driver, "//*[@data-attr]", 30);
		
		List<WebElement> primaryNav = driver.findElements(By.xpath("//*[@data-attr]"));
		Iterator<WebElement> primaryNavItr = primaryNav.iterator();
		System.out.println (primaryNav.size());
		WebElement mainMenuEle = null;
		
		while (primaryNavItr.hasNext()) {
			WebElement eachPrimaryNav = (WebElement) primaryNavItr.next();
			String mainMenu = eachPrimaryNav.findElement(By.xpath(".//*[@class='primary-nav-item-label']")).getText();
			System.out.println (mainMenu);			
			if (mainMenu.equalsIgnoreCase(menus[0])){
				mainMenuEle = eachPrimaryNav;
				//break;
			}
		}
		
		if (mainMenuEle==null){
			throw new Exception (menus[0] + " not found");
		}
		try{
			if (! mainMenuEle.findElement(By.xpath(".//a[@class='primary-nav-carat']")).isDisplayed()){
				mainMenuEle.click();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println ("********************************************************");
		if (menus.length>1){
			List<WebElement> subMenuElements = mainMenuEle.findElements(By.xpath(".//*[@class='primary-nav-sub-item']/a"));
			Iterator<WebElement> subMenuItr = subMenuElements.iterator();
			while (subMenuItr.hasNext()) {
				WebElement subMenuEle = (WebElement) subMenuItr.next();
				String subMenutxt = subMenuEle.getText();
				System.out.println (subMenutxt);
				if (subMenutxt.equalsIgnoreCase(menus[1])){
					subMenuEle.click();
				}
				
			}
		}

	}
	
	/**
	 * 
	 * @param driver
	 * @param itemLink
	 * @param itemName
	 */
	public void selectItem(WebDriver driver, String itemLink, String itemName){
		try {
			driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='"+ (itemLink) +"']]/descendant::button")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[input[@placeholder='"+ (itemLink) +"']]/descendant::li/div[text()='"+ (itemName) +"']")).click();
		} catch (InterruptedException e) {

		}
	}
	
	
	
	public boolean isAlertPresent(WebDriver driver){
        try{
            driver.switchTo().alert();
            return true;
        }//try
        catch(Exception e){
        	e.printStackTrace();
            return false;
        }//catch
    }
	
	public boolean scrollUntillElementFound (WebDriver driver, WebElement scrollElement, By byElementLocator){

		Actions dragger = new Actions(driver);
		//WebElement draggablePartOfScrollbar = driver.findElement(By.xpath("//div[@class='jb-ace-scroll-wrapper jb-ace-scroll jb-ace-scroll-orientation-vertical']//div[@class='jb-ace-scroll-scrollbar-middle']"));
		int numberOfPixelsToDragTheScrollbarDown = 100;

	    for (int i=10 ; i < 500; i=i+numberOfPixelsToDragTheScrollbarDown){
	    	try{
	    		// this causes a gradual drag of the scroll bar, 10 units at a time
	    		System.out.println("*************FirstName Start************");
	    		if (isElementPresent(driver, byElementLocator)){
	    			System.out.println("found element");
	    			if (driver.findElement(byElementLocator).isDisplayed()){
	    				System.out.println("element displayed");
	    				return true;
	    			}
	    			
	    		}
	    		System.out.println("*************FirstNameEnd************");
	    		dragger.moveToElement(scrollElement).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	    		Thread.sleep(1000L);
	    	}catch(Exception e1){}
	    } 
	    
	    return false;
	   
	}
	
	
	
	public void selectItemSpan(WebDriver driver, String itemLink, String itemName ){
		try {
			driver.findElement(By.xpath("//div[input[normalize-space(@placeholder)='"+ (itemLink) +"']]/descendant::button")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[input[@placeholder='"+ (itemLink) +"']]/descendant::li/span[normalize-space(text())='"+ (itemName) +"']")).click();
		} catch (InterruptedException e) {

		}
	}
	
	
	/**
	 * Performs the mouse over operation on the specified element
	 * @param webDriver
	 * @param element
	 */
	public void mouseOverElement(WebDriver webDriver, WebElement element){

		Locatable hoverItem = (Locatable) element;
		Mouse mouse = ((HasInputDevices) webDriver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());
	}


	public String clickOnNonBlankLastNameContactLink (WebDriver driver) throws Exception{
		waitForElement(driver, "//table[@class='ussr-table-striped']", 30);
		List<WebElement> findElements = driver.findElements(By.xpath("//table[@class='ussr-table-striped']//td[2]/span/a"));
		Iterator<WebElement> iterator = findElements.iterator();
		while (iterator.hasNext()) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println("linkname:"+webElement.getText());
			System.out.println(webElement.getText().trim().length());
			System.out.println("");
			String contactName = webElement.getText().trim();
			if (contactName.length()!=0){
				webElement.click();
				return contactName;
			}
			
		}
		throw new Exception ("No Contacts Found");
	}
	
	public String selectNonBlankLastNameContactLink (WebDriver driver) throws Exception{
		
		waitForElement(driver, "//table[@class='ussr-table-striped']", 30);
		List<WebElement> findElements = driver.findElements(By.xpath("//table[@class='ussr-table-striped']//td[2]/span/a"));
		Iterator<WebElement> iterator = findElements.iterator();
		while (iterator.hasNext()) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println("linkname:"+webElement.getText());
			System.out.println(webElement.getText().trim().length());
			System.out.println("");
			String contactName = webElement.getText().trim();
			if (contactName.length()!=0){
				webElement.findElement(By.xpath("/descendant::td[2]")).click();
				//webElement.click();
				return contactName;
			}
			
		}
		throw new Exception ("No Contacts Found");
	}
	
	public String selectContactsCheckbox (WebDriver driver, String contactName) throws Exception{
		waitForElement(driver, "//table[@class='ussr-table-striped']", 30);
		List<WebElement> findElements = driver.findElements(By.xpath("//table[@class='ussr-table-striped']//td[2]/span/a"));
		Iterator<WebElement> iterator = findElements.iterator();
		while (iterator.hasNext()) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println("linkname:"+webElement.getText());
			System.out.println(webElement.getText().trim().length());
			System.out.println("");
			String appContactName = webElement.getText().trim();
			if (appContactName.equalsIgnoreCase(contactName)){
				webElement.findElement(By.xpath("/descendant::td[2]")).click();
				//webElement.click();
				return contactName;
			}
			
		}
		throw new Exception ("No Contacts Found");
	}
	
	
	

}
