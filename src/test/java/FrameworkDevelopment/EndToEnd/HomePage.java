package FrameworkDevelopment.EndToEnd;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class HomePage extends Base {

	public WebDriver driver;
	@BeforeTest
	public void openBrowser() throws IOException, InterruptedException {
		driver = intializeBrowser();
	}
	
	@Test(dataProvider = "getData")
	public void basePageNavigation(String username, String pswd, String text) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LandingPage l = new LandingPage(driver);
		
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.visibilityOf(l.signInButton()));
		
		l.signInButton().click();
		LoginPage logIn = new LoginPage(driver);
		logIn.emailAddress().sendKeys(username);
		logIn.password().sendKeys(pswd);
		System.out.println(text);
		logIn.logInButton().click();
		
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "restircited_user@gmail.com";
		data[0][1] = "658191";
		data[0][2] = "Restricted User";
		
		data[1][0] = "non_restircited_user@gmail.com";
		data[1][1] = "6ss58191";
		data[1][2] = "Non Restricted User";
		return data;
				
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
}
