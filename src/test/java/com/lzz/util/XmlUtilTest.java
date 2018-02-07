package com.lzz.util;

import org.junit.Test;

import java.util.Map;

/**
 * Created by lzz on 2018/2/7.
 */
public class XmlUtilTest {
    @Test
    public void test001() throws Exception {
        XmlUtil xmlUtil = new XmlUtil("user.xml");
        xmlUtil.add("aaa111","cccc");
        XmlUtil xmlUtil2 = new XmlUtil("user2.xml");
        xmlUtil2.add("aaa2222","cccc");
    }

    @Test
    public void test002(){
        XmlUtil xmlUtil1 = new XmlUtil("user.xml");
        Map map = xmlUtil1.getAllMap();
        System.out.println( map );
    }

    @Test
    public void test003(){
        XmlUtil xmlUtil1 = new XmlUtil("user.xml");
        xmlUtil1.remove("aaa111");
        XmlUtil xmlUtil2 = new XmlUtil("user2.xml");
        xmlUtil2.remove("aaa2222");
    }

    @Test
    public void test004(){
        System.out.println( PropertiesUtil.get("server.port") );

        //String path = XmlUtil.class.getResource("/").toString();
        //System.out.println("path = " + path);
    }
}
