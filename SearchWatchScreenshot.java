package example;


import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SearchWatchScreenshot {
	
	public static void takepicture(WebDriver driver, String filename) throws Exception {
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(source, new File(".//screenshots//" + filename +".png"));
}
	
public static void main(String[] args) throws Exception {
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\Share\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.amazon.in");	
		String firstTab = driver.getWindowHandle();
		driver.manage().window().maximize();
		Actions action = new Actions(driver);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Watches");
		Thread.sleep(3000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
		
			WebElement  result = driver.findElement(By.cssSelector("img[data-image-index=\"3\"]"));
			
			action.moveToElement(result).build().perform();
			
			
		
		result.click();
		//getting all windows
		Set<String> nextTabs = driver.getWindowHandles();
		
		for (String win : nextTabs) {
			if(!firstTab.equals(win)) {
				
				driver.switchTo().window(win);
				
				takepicture(driver, "imgwatch");
				Thread.sleep(3000);
				driver.close();
			}
		}
		driver.switchTo().window(firstTab);
		Thread.sleep(3000);	
		driver.close();
		
}
}
