package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.crm.qa.utils.TestUtil;
import com.crm.qa.utils.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("deprecation")
public class TestBase {

	public static WebDriver  driver;
    public static Properties prop; 
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventlistener;
    
	public TestBase(){
		try {
			prop=new Properties();
			FileInputStream file=new FileInputStream(new File("/Users/chiragbansod/eclipse-workspace"
					+ "/FreeCRMMavenProject/src/main/java/com/crm/qa/config/config.properties"));
			prop.load(file);
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void initialization() {
		String browserName=prop.getProperty("browser");
	    if(browserName.equalsIgnoreCase("chrome")) {
	    	driver=WebDriverManager.chromedriver().create();
	    }else if(browserName.equalsIgnoreCase("firefox")) {
	    	driver=WebDriverManager.firefoxdriver().create();
	    }else if(browserName.equalsIgnoreCase("edge")) {
	    	driver=WebDriverManager.edgedriver().create();
	    }else if(browserName.equalsIgnoreCase("safari")) {
	    	driver=WebDriverManager.safaridriver().create();
	    }
	    
	    e_driver=new EventFiringWebDriver(driver);
	    eventlistener=new WebEventListener();
	    e_driver.register(eventlistener);
	    driver=e_driver;
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
	    driver.get(prop.getProperty("url"));
	    
	}
	
}
