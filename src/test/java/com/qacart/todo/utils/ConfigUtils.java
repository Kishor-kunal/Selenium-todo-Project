package com.qacart.todo.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    //    public static void main(String[] args) throws IOException {
//
//        InputStream is =new FileInputStream("src/test/resources/env/local.properties");
//        Properties prop=new Properties();
//        prop.load(is);
//        System.out.println(prop.get("URL"));
//    }
    private static ConfigUtils configUtils;
    private Properties prop;

    private ConfigUtils() {
        prop=readProp();
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    private Properties readProp() {
        InputStream is;
        try {
            String env=System.getProperty("env","PRODUCTION");
            switch (env) {
                case "PRODUCTION" ->{
                    is = new FileInputStream("src/test/resources/env/production.properties");
                }
                case "LOCAL" ->{
                    is = new FileInputStream("src/test/resources/env/local.properties");
                }
                default -> {
                    throw new RuntimeException("Environment is not Supported!");
                }
            }

            prop = new Properties();
            prop.load(is);
            System.out.println(prop.get("URL"));
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return prop;
    }
    public String getBaseUrl(){
        return prop.get("URL").toString();
    }
}
