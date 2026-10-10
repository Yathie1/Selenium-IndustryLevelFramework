package tests;

import org.testng.annotations.Test;

import testdata.LoginData;
import testdata.LoginDataSet;
import utils.JsonDataReader;

public class JsonDataTest {

    @Test
    public void readLoginData() {

        LoginDataSet data =
                JsonDataReader.readJson(
                        "src/test/resources/testdata/login.json",
                        LoginDataSet.class
                );

        LoginData validUser = data.getValidUser();

        System.out.println(
                "Username: " + validUser.getUsername()
        );

        System.out.println(
                "Password: " + validUser.getPassword()
        );
    }
}