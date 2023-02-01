package com.crm.qa.testcases;


import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactsPage;
	String sheetname="contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod()
	public void setup() {
		initialization();
		testutil=new TestUtil();
		contactsPage = new ContactsPage();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchtoFrame("mainpanel");
	}
	
	@Test(priority=1)
	public void verifyContactsLabel() {
		homepage.clickOnContactsLink();
	    Assert.assertTrue(contactsPage.verifyContactslabel(),"Contacts label is not displayed");
	    driver.switchTo().parentFrame();
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		homepage.clickOnContactsLink();
		driver.switchTo().parentFrame();
		contactsPage.selectContactsByName("Naveen khunteta");
	}
	
	@Test(priority = 3 )
	public void selectMultiple() {
		homepage.clickOnContactsLink();
		driver.switchTo().parentFrame();
		contactsPage.selectContactsByName("Naveen khunteta");
		contactsPage.selectContactsByName("harish kothnur");
	}
	
	@DataProvider()
	public Object[][] getCRMTestData() throws FileNotFoundException {
	  Object[][] data=	TestUtil.getTestData(sheetname);
		return data;
	}
	
	@Test(priority = 4,dataProvider = "getCRMTestData")
	public void validateCreateNewContactTest(String title,String fname,String lname,String company) {
		homepage.clickOnNewContactsLink();
		contactsPage.createNewContact(title,fname,lname,company);
	}
	
	@AfterMethod()
	public void teardown() {
		driver.quit();
	}
}
