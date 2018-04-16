package myfixture;


import java.util.List; 
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;

public class Base {
	final static Logger logger = Logger.getLogger(fixtureExample.class);
	public static WebDriver driver;
	 

	// Launch website from firefox browser.
	public static void launchFirefox(String url) throws Throwable {

		logger.debug("Base: Opening the Firefox browser ");
		System.setProperty("webdriver.firefox.marionette", "C:\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		logger.debug("Base: Browser opened successfully");
		driver.get(url);
		Thread.sleep(2000);
		logger.debug("home page of url is launched");		
		 
	}	 

	// Search on google by strings.
	public void searchOnGoogle(String searchStr) {		 		 
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys(searchStr);	
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.RETURN);
		//driver.findElement(By.xpath("//[@name='btnK']")).click();
		 
				 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Find wiki page for selenium.
	public void click_wiki_page(String seleniumWiki) {

		WebElement requiredElement = driver.findElement((By.cssSelector("a[href*='wiki/Selenium']")));		 
		if (requiredElement != null) {
			requiredElement.click();
			
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
	}
	
	public boolean is_selenium_wiki_page_launched(String seleniumWiki){
		String currentURL = driver.getCurrentUrl();
		//WebElement requiredElement =driver.findElement(By.xpath("//*[@id='firstHeading']/text()"));
		WebElement requiredElement =driver.findElement(By.id("firstHeading"));
		String title = requiredElement.getText();
		if(seleniumWiki.equalsIgnoreCase(currentURL)&&title.contains("Selenium")){
			return true;
		}
		return false;
	}

	// get element of main content.
	public WebElement get_Main_Content() {
		List<WebElement> aTagsList = driver.findElements(By.tagName("a"));
		WebElement requiredElement = null;

		int tag_index = 0;
		while (aTagsList != null) {
			requiredElement = aTagsList.get(tag_index);
			if (requiredElement.getAttribute("id").equals("main-content")) {
				return requiredElement;
			}
			tag_index++;
		}
		return requiredElement;
	}

	// get picture list in main content.
	public List<WebElement> pictures_In_Main_Article() {
		List<WebElement> aTagsList = driver.findElements(By.tagName("a"));
		WebElement requiredElement = null;
		List<WebElement> imageList = null;
		int tag_index = 0;
		while (aTagsList != null) {
			requiredElement = aTagsList.get(tag_index);
			System.out.print(requiredElement.getTagName());
			if (requiredElement.getAttribute("id").equals("main-content")) {
				imageList = requiredElement.findElements(By.className("file file-image file-image-jpeg"));

			}
			tag_index++;
		}
		return imageList;
	}

	// click on the image of main article.
	public WebElement clickOnMainArticle(List<WebElement> imageList) {
		WebElement image = imageList.get(0);
		image.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;

	}

	public boolean is_click_Page_loaded(WebElement image) {
		String text = image.getText();
		WebElement textFound = driver.findElement(By.linkText(text));
		if (textFound != null) {
			return true;
		}
		return false;
	}
}
