package com.soccerconnect.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader implements ConfigReaderInterface {

    Properties properties = new Properties();

    public ConfigReader(String propFileName) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public String getConfigValue(String config) throws Exception {
        if (properties.getProperty(config) != null) {
            return properties.getProperty(config);
        } else {
            throw new Exception("Config " + config + " does not exist!");
        }
    }
}
