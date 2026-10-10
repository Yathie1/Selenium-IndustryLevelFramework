package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyDemo {

    @Test
    public void loginTest() {

        System.out.println("Login test executed");
        Assert.fail("Login failed");
    }

    @Test(dependsOnMethods = "loginTest")
    public void productTest() {

        System.out.println("Product test executed");
    }

    @Test(dependsOnMethods = "productTest")
    public void checkoutTest() {

        System.out.println("Checkout test executed");
    }
}