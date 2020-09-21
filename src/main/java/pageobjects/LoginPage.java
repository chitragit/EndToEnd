package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
	this.driver = driver;	
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="user_email")
	private WebElement emailAddress;
	
	
	@FindBy(id="user_password")
	private WebElement password;
	
	@FindBy(xpath="//input[@value='Log In']")
	private WebElement loginButton;
	
	
	public WebElement emailAddress() {
		return emailAddress;
	}
	
	public WebElement password() {
		return password;
	}
	
	public WebElement logInButton() {
		return loginButton;
	}
	
	
}
