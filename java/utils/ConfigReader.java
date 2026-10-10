package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        try {

            properties = new Properties();

            // Load common configuration
            FileInputStream configFile =
                    new FileInputStream(
                            "src/test/resources/config/config.properties"
                    );

            properties.load(configFile);
            configFile.close();

            // Read environment
            String environment =
                    System.getProperty(
                            "environment",
                            properties.getProperty("environment")
                    );

            if (environment == null || environment.trim().isEmpty()) {
                throw new RuntimeException(
                        "Environment is not specified in config.properties"
                );
            }

            // Environment configuration path
            String environmentPath =
                    "src/test/resources/config/"
                    + environment
                    + ".properties";

            File environmentFile =
                    new File(environmentPath);

            // Validate environment file
            if (!environmentFile.exists()) {

                throw new RuntimeException(
                        "Environment configuration not found: "
                        + environment
                );
            }

            // Load environment-specific configuration
            FileInputStream environmentInput =
                    new FileInputStream(environmentFile);

            Properties environmentProperties =
                    new Properties();

            environmentProperties.load(environmentInput);
            environmentInput.close();

            // Merge environment properties
            properties.putAll(environmentProperties);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load configuration files",
                    e
            );
        }
    }

    // Get property without validation
    public static String getProperty(String key) {

        return properties.getProperty(key);
    }

    // Get property with validation
    public static String getRequiredProperty(String key) {

        String value =
                properties.getProperty(key);

        if (value == null || value.trim().isEmpty()) {

            throw new RuntimeException(
                    "Required configuration missing: "
                    + key
            );
        }

        return value;
    }
}