package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

    private static Properties properties;

    static {

        try {

            properties = new Properties();

            FileInputStream file =
                new FileInputStream(
                    "src/test/resources/testdata/login.properties"
                );

            properties.load(file);

        } catch (IOException e) {

            throw new RuntimeException(
                "Unable to load login test data",
                e
            );
        }
    }

    public static String get(String key) {

        String value =
            properties.getProperty(key);

        if (value == null || value.trim().isEmpty()) {

            throw new RuntimeException(
                "Test data not found: " + key
            );
        }

        return value;
    }
}