package org.example.preferences;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private static final Properties PROPERTIES = new Properties();

    public String getPath() throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("hibernate.properties");

        PROPERTIES.load(inputStream);
        return PROPERTIES.getProperty("hibernate.connection.url");
    }

}
