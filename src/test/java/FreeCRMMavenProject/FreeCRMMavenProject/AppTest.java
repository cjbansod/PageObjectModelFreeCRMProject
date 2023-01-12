package FreeCRMMavenProject.FreeCRMMavenProject;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test()
    public void shouldAnswerWithTrue()
    {
        Assert.assertTrue( true );
    }
    
    @Test()
    public void launchBrowser() {
    	WebDriver driver= WebDriverManager.chromedriver().create();
    	driver.get("https://www.google.com/");	
    	System.out.println(driver.getCurrentUrl());
    	System.out.println(driver.getTitle());
    }
}
