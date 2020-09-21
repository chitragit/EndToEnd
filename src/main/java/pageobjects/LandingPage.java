package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	public WebDriver driver;
	
//constructor initialize driver
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Encapsulation - hiding data variable and restrict them to access only via pub methods
	
	@FindBy(css = "a[href*='sign_in']")
	private WebElement signInButton;
	
	@FindBy(xpath = "//section[@id='content']/div/div")
	private WebElement title;
	
	@FindBy(css = "nav[class='navbar-collapse collapse'] ul")
	private WebElement navBar;
	
	public WebElement signInButton() {
		return signInButton;
	}
	
	public WebElement titleFeatured() {
		return title;
	}
	
	public WebElement navBar() {
		return navBar;
	}
	
	
	
	
}
