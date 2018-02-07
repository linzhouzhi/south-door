package com.lzz.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lzz on 2018/2/7.
 */
public class PropertiesUtil {
    private static Map<String, String> propertiesMap = new HashMap<>();

    static {
        loadProperties("application.properties");
    }

    public static String get(String field){
        return propertiesMap.get( field );
    }

    private static void loadProperties(String filename){
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadAllProperties(filename);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements())
            {
                String key = (String) en.nextElement();
                String value = props.getProperty(key);
                propertiesMap.put( key, value );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
