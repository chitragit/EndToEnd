package FrameworkDevelopment.EndToEnd;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import resources.Base;

public class ValidateTitile extends Base {

	//local copy, inorder to make test run parllely
	public WebDriver driver;
	
	@BeforeTest
	public void openBrowser() throws IOException, InterruptedException {
		driver = intializeBrowser();
		driver.get(prop.getProperty("url"));
	}

	@AfterTest
	public void teatDown() {
		driver.close();
	}

	@Test
	public void validateTitle() throws IOException, InterruptedException {

		LandingPage l = new LandingPage(driver);
		Assert.assertEquals(l.titleFeatured().getText(), "FEATURED COURSES123");
		System.out.println(l.navBar().getText());
		Assert.assertFalse(l.navBar().isDisplayed());

	}
	
	
}
