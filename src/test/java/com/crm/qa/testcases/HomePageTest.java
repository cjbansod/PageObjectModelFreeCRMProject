package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class HomePageTest extends HomePage {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactpage;
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod()
	public void setup() {
		initialization();
		testutil=new TestUtil();
		contactpage=new ContactsPage();
		loginpage=new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homepagetitle=homepage.verifyHomePageTitle();
		Assert.assertEquals(homepagetitle, "CRMPRO","Home page title not matched");
	}
	
	@Test(priority = 2)
	public void verifyCorrectUsername() {
		testutil.switchtoFrame("mainpanel");
		Assert.assertTrue(homepage.verifyCorretUsername());
	}
	
	@Test(priority = 3)
	public void verifyContactsLinkTest() {
	    testutil.switchtoFrame("mainpanel");
	    contactpage = homepage.clickOnContactsLink();
	}
	
	@AfterMethod()
	public void teardown() {
		driver.quit();
	}
	
}
