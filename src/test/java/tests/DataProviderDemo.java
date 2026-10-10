package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {
	
	@DataProvider(name = "logindata")
	public Object[][] logindata() {
		return new Object[][]{
            {"standard_user", "secret_sauce"},
            {"problem_user", "secret_sauce"},
            {"locked_out_user", "secret_sauce"}
        };
	}

	
	 @Test(dataProvider = "logindata")
	    public void loginTest(String username, String password) {

	        System.out.println("Username: " + username);
	        System.out.println("Password: " + password);
	    }
	
	
	
	
	
	
}
