package tests;

import org.testng.annotations.Test;

public class ProductTest {

    @Test(groups = "smoke")
    public void verifyProductPage() {
        System.out.println("Product test executed");
    }
}