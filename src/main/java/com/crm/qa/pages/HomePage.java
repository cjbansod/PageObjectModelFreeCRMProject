package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.utils.TestUtil;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	
	@FindBy(xpath="//td[contains(text(),'chirag bansod')]")
	WebElement usernameHomePage;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyCorretUsername() {
		return usernameHomePage.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactsLink() {
		TestUtil testutil=new TestUtil();
		Actions action=new Actions(driver);
		action.moveToElement(contactLink).build().perform();
		boolean flag=testutil.waitforElement(newContactLink);
	    if(flag) {
		newContactLink.click();
	    }
	}
	
	
	
	
}
