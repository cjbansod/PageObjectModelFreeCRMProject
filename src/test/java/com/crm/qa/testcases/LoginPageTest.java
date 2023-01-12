package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{

	LoginPage login;
	HomePage homepage;
	public LoginPageTest() {
		super();
		
	}
	@BeforeMethod()
	public void setUp() {
		initialization();
	    login=new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title=login.validateLoginPageTile();
	    Assert.assertEquals(title, "Free CRM - CRM software for customer relationship management, sales, and support.");
	}
	@Test(priority=2)
	public void validateCRMLogoTest() {
		boolean flag=login.validateCRMImage();
        Assert.assertTrue(flag);	
	}
	
	@Test(priority=3)
	public void loginTest() {
		homepage=login.login(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}
