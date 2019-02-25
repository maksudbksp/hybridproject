package operationPack;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UIOperation extends AbstractClass{
	
	public UIOperation(WebDriver driver) {
		this.driver=driver;
		//why keyword k smaller but xls file k capital(all same)
	}
	public void PerformKeyWord(Properties p, String keyword, String objectName, String objectType, String data) throws Exception {
		switch(keyword.toUpperCase()) {
		case "GOTOURL":
			driver.get(p.getProperty(data));
			break;
			
		case "SENDKEYS":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(data);
			break;
			
		case "CLICK":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			break;
			
		case "CLEAR":
			driver.findElement(this.getObject(p, objectName, objectType)).clear();
			break;
			
		case "SELECT":
			new Select(driver.findElement(this.getObject(p, objectName, objectType))).selectByVisibleText(data);
			break;
		case "GETTEXT":
			String text=driver.findElement(this.getObject(p, objectName, objectType)).getText();
			System.out.println("Text is : "+text);
			break;
		case "GETTITLE":
			String title=driver.getTitle();
			System.out.println("Title is : "+title);
			break;
		case "GETCURRENTURL":
			String Str=driver.getCurrentUrl();
			System.out.println("Currenturl is :"+Str);
			break;
		case"IMAGEVERIFY":
			boolean Image=driver.findElement(this.getObject(p, objectName, objectType)).isDisplayed();
			if(Image==true) {
				System.out.println("Image is displayed");
			}
			else {
				System.out.println("Image is not displayed");
			}
			break;
			
		case "HIGHLIGHT":
			JavascriptExecutor jse=((JavascriptExecutor)driver);
			WebElement element=driver.findElement(this.getObject(p, objectName, objectType));
			jse.executeScript("arguments[0].setAttribute('style', 'background: ; border: 2px solid red;');", element);
			break;
			
		case "SCREENSHOT":
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f, new File("D:\\ITTraning\\Facebook.jpg"));
			break;
				
		case "SUBMENUHANDLING":
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(this.getObject(p, objectName, objectType))).build().perform();
			break;
		case "ALERTHANDLING":
			Alert alt=driver.switchTo().alert();
			alt.accept();
			break;
		case "MAINWINDOW":
			ArrayList<String>oldTab=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(oldTab.get(0));
			break;
			
		case "CHILDWINDOW1":
			ArrayList<String>newTab1=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newTab1.get(1));
			break;
			
		case "CHILDWINDOW2":
			ArrayList<String>newTab2=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newTab2.get(2));
			break;
			
		case "THREAD":
			Thread.sleep(3000);
			break;
		case "CLOSE":
			driver.close();
			break;
		case "QUIT":
			driver.quit();
			break;
			
		}
		
	}
	//why i can'mt run all classes except hybrid test case
	//how we do declared locator?is always like this
	//what is private
	private By getObject(Properties p, String objectName, String objectType) throws Exception {
		if(objectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CSSSELECTOR")) {
			return By.cssSelector(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("LINKTEXT")) {
			return By.linkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("PARTIALLINKTEXT")) {
			return By.partialLinkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(p.getProperty(objectName));
		}
		else{
			
			throw new Exception("Wrong object type");
		}
	}
}
