package caseItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewT {

  public WebDriver driver; 	
	
  @Test 
  public void RedBus_HomePage() throws InterruptedException {
	
	  //Source
	  WebElement source=driver.findElement(By.id("src"));
	  source.sendKeys("Coimbatore");
	  Thread.sleep(3000);
	  //source  
	  List<WebElement> place_list=driver.findElements(By.className("autoFill"));  
	  for(WebElement Elements_place: place_list) {
		  System.out.println(Elements_place.getText()); 
		  if(Elements_place.getText().equals("Coimbatore")) {
			  Elements_place.click();
			  break;
		  }
	  }

	 /* WebElement countryUL= driver.findElement(By.xpath("//*[@id=\"search\"]/div/div[1]/div/ul"));
	  List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
	  for (WebElement li : countriesList) {
		  System.out.println("The WebLists\n:" +li.getText());
	  if (li.getText().equals("Coimbatore")) {
	       li.click();
	       break;
	     }
	  }*/
	  Thread.sleep(3000);
	  //Dest
	  WebElement dest=driver.findElement(By.id("dest"));  
	  dest.sendKeys("Chennai");
	  Thread.sleep(3000);
	  //Dest.Click
	  WebElement countryUL= driver.findElement(By.xpath("//*[@id=\"search\"]/div/div[2]/div/ul"));
	  List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
	  for (WebElement li : countriesList) {
		  System.out.println("The WebLists\n:" +li.getText());
	  if (li.getText().equals("Chennai")) {
	       li.click();
	       break;
	     }
	  }
	  
	  WebElement onward=driver.findElement(By.id("onward_cal"));
	   
  }
  
  @BeforeMethod
  public void setup() throws Exception {
	 
	  //Setting proxy
	  Proxy proxy=new Proxy();
	  proxy.setHttpProxy("your Url:port");
	  proxy.setSslProxy("Your Url:port");
	  
	  DesiredCapabilities dc=DesiredCapabilities.chrome();
	  dc.setCapability("proxy", proxy);
	  
	  //Disable chrome notifications 
	  ChromeOptions ops=new ChromeOptions();
	  ops.addArguments("--disable-notifications");
	  ops.addArguments("start-maximized");
	  
	  dc.setCapability(ChromeOptions.CAPABILITY,ops);
	  
	  //Setting the Chrome driver by Set property
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\praveen kumar J\\Documents\\ChromeDriver\\chromedriver.exe");
	  driver=new ChromeDriver(ops);
	  
	  //URL to test the application
	  driver.get("https://www.redbus.in/");
	  
	  //To get full screen of chrome browser
	  driver.manage().window().maximize();
	  
	  
	  //Implicity wait conditions 
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Checking broken Links
	  try {
		URL link=new URL("https://www.redbus.in/");
		
		HttpURLConnection httpcon=(HttpURLConnection)link.openConnection();
		
		httpcon.setConnectTimeout(2000);
		
		httpcon.connect();
		
		if(httpcon.getResponseCode()==200) {
			System.out.println("The URL"+httpcon.getResponseMessage());
		}
		if(httpcon.getResponseCode()==404) {
			System.out.println("The URL"+httpcon.getResponseCode());
		}
		
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }
  
  @AfterMethod
  public void closure() {
	 // driver.close();
  }
  
}
