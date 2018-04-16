package myfixture;

import static org.junit.Assert.*;
 
import org.apache.log4j.Logger;
import org.junit.Test;

import fitlibrary.DoFixture;
 
import org.openqa.selenium.WebDriver;
 
 

public class fixtureExample extends DoFixture {
	final static Logger logger = Logger.getLogger(fixtureExample.class);
	WebDriver driver;
	Base base = new Base();
	String searchStr = "selenium";	 
	String url = "https://www.google.com";
	String selenium_wiki = "https://en.wikipedia.org/wiki/Selenium_(software)";

	@Test
	public void testSearchFromGoogle() {

		try {
			Base.launchFirefox(url);			 
			base.searchOnGoogle(searchStr);
			base.click_wiki_page(selenium_wiki);
			boolean selenium_wiki_page_launched = base.is_selenium_wiki_page_launched(selenium_wiki);
			System.out.println("Does wiki page for selenium is loaded? " + selenium_wiki_page_launched);
			assertTrue("Wiki page for Selenium is not loaded", selenium_wiki_page_launched);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 
}
