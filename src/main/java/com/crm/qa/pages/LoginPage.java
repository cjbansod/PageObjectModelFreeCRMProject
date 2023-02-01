package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.utils.TestUtil;

public class LoginPage extends TestBase{

	TestUtil testutil;
	
	//object repository
	@FindBy(name="username")
    WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
    WebElement loginbtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo; 
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
		testutil=new TestUtil();
	}
	
	//Actions
	public String validateLoginPageTile() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		testutil.waitforElement(loginbtn);
		this.loginbtn.click();
		return new HomePage();
		
	}
	
}
