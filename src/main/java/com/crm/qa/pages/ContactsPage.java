package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactslabel;
	
	@FindBy(name =  "title")
	WebElement titleddl;
	
	@FindBy(id="first_name")
	WebElement firstnametxt;
	
	@FindBy(id="surname")
	WebElement lastnametxt;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//form[@id='contactForm']//input[@type='submit' and @value='Save']")
	WebElement savebtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactslabel() {
		return contactslabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) {
		WebElement element=driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
	}
	
	public void createNewContact(String title,String ftname,String ltname,String copmanyname) {
		Select select=new Select(titleddl);
		select.selectByVisibleText(title);
		firstnametxt.sendKeys(ftname);
		lastnametxt.sendKeys(ltname);
		company.sendKeys(copmanyname);
		savebtn.click();
		
	}
}
