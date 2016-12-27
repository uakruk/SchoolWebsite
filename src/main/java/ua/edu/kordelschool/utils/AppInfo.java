package ua.edu.kordelschool.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AppInfo {

    static {
        InputStream is =
                AppInfo.class.getClassLoader().getResourceAsStream("my.properties");
        Properties p = new Properties();
        try {
            p.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PROJECT_VERSION = p.getProperty("projVersion");
    }

    public static final String PROJECT_VERSION;
}
