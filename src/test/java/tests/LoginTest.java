package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	
	@Test
	public void VerifyLoginTest() {
		driver.get("https://www.saucedemo.com/");
		LoginPage loginpage = new LoginPage( driver);
		loginpage.login("standard_user", "secret_sauce");
		
		Assert.assertEquals(
			    driver.getTitle(),
			    "Swag Labs"
			);
		
		Assert.assertEquals(
	            driver.getCurrentUrl(),
	            "https://www.saucedemo.com/inventory.html");
		
		Assert.assertTrue(
			    driver.getCurrentUrl().contains("inventory")
			);
		
		
	}

}
