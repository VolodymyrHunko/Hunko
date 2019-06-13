package coreJava;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

    /**
     * class to read Configuration file to get all common properties
     * to use it in Selenium tests
     */
public  class ConfigFileReader {
        private Properties prop;
        private final String propPath = System.getProperty("user.dir") + "/src/test/java/seleniumTesting/Configuration.properties";
        private BufferedReader br;

        ConfigFileReader() {
            try {
                br = new BufferedReader(new FileReader(propPath));
                prop = new Properties();
                prop.load(br);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("File not found on: " + propPath);
            }
        }

        public String getUrlZoom(){
            String value = prop.getProperty("urlZoom");
            if(value!=null) {
                return value;
            }
            else {
                throw new RuntimeException("No property in this file was found...");
            }
        }

        public String getUrlTestQA(){
            String value = prop.getProperty("urlTestQA");
            if(value!=null) {
                return value;
            }
            else {
                throw new RuntimeException("No property in this file was found...");
            }
        }

        public String getBase(){
            String value = prop.getProperty("base");
            if(value!=null) {
                return value;
            }
            else {
                throw new RuntimeException("No property in this file was found...");
            }

        }

        public String getBrowser(){
            String value = prop.getProperty("browser");
            if(value!=null) {
                return value;
            }
            else {
                throw new RuntimeException("No property in this file was found...");
            }

        }

        public String getUrlJQuery(){
            String value = prop.getProperty("urlJQuery");
            if(value!=null) {
                return value;
            }
            else {
                throw new RuntimeException("No property in this file was found...");
            }

        }

        @Test
        void testConfigFile() {
            System.out.println(new coreJava.ConfigFileReader().getUrlTestQA());
            System.out.println(SingletonClass.getInstance().getReader().getUrlJQuery());
        }
    }

