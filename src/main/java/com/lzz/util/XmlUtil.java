package com.lzz.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lzz on 2018/2/7.
 */
public class XmlUtil {
    private static String BASE_PATH = "";
    private static Map<String, ConcurrentHashMap<String, String>> mxlMap = new HashMap();
    private String fileName;

    public XmlUtil(String fileName){
        synchronized (this){
            this.fileName = fileName;
            Map<String, String> fileMap = mxlMap.get( getFile( fileName ) );
            if( null == fileMap ){
                mxlMap.put( fileName, new ConcurrentHashMap<>());
            }
        }
    }

    public boolean add(String key, String value){
        boolean addRes = false;
        boolean deleteRes = deleteRow(this.fileName, key);
        if( deleteRes ){
            addRes = addRow( this.fileName, key, value);
        }
        return addRes && deleteRes;
    }

    public boolean remove(String key){
        return deleteRow(this.fileName, key);
    }

    public Map<String, String> getAllMap(){
        Map<String, String> resMap = new HashMap<>();
        try {
            resMap = readXml(this.fileName);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    private boolean deleteRow(String fileName, String key){
        boolean res = true;
        Map<String, String> fileMap = mxlMap.get( getFile( fileName ) );
        synchronized( fileMap ){
            try {
                SAXReader reader = new SAXReader();
                Document doc = reader.read( getFile(fileName) );
                Element root = doc.getRootElement();
                Element element;
                for (Iterator i = root.elementIterator("element"); i.hasNext();) {
                    element = (Element) i.next();
                    String keyName = element.attributeValue("key");
                    if( key.equals( keyName )){
                        element.detach();
                    }
                }
                Writer out = new PrintWriter( getFile(fileName), "UTF-8");
                OutputFormat format = new OutputFormat("\t", true);
                format.setTrimText(true);
                XMLWriter writer = new XMLWriter(out, format);
                writer.write(doc);
                out.close();
                writer.close();
                fileMap.remove( key );
            } catch (Exception e) {
                e.printStackTrace();
                res = false;
            }

        }
        return res;
    }



    private boolean addRow(String fileName, String key, String value){
        boolean res = true;
        Map<String, String> fileMap = mxlMap.get( getFile( fileName ) );
        synchronized( fileMap ){
            try {
                SAXReader reader = new SAXReader();
                Document doc = reader.read( getFile(fileName) );
                Element root = doc.getRootElement();
                Element element = root.addElement( "element" );
                element.addAttribute("key", key);
                element.addAttribute("value", value);
                Writer out = new PrintWriter( getFile(fileName), "UTF-8");
                OutputFormat format = new OutputFormat("\t", true);
                format.setTrimText(true);
                XMLWriter writer = new XMLWriter(out, format);
                writer.write(doc);
                out.close();
                writer.close();
                fileMap.put( key, value );
            }catch (Exception e){
                res = false;
            }
        }
        return res;
    }

    private Map<String, String> readXml(String fileName) throws DocumentException {
        Map<String, String> elements = new HashMap<>();
        File f = new File( getFile(fileName) );
        SAXReader reader = new SAXReader();
        Document doc = reader.read(f);
        Element root = doc.getRootElement();
        Element element;
        for (Iterator i = root.elementIterator("element"); i.hasNext();) {
            element = (Element) i.next();
            String key = element.attributeValue("key");
            String value = element.attributeValue("value");
            elements.put( key, value );
        }
        return elements;
    }

    private String getFile(String filename){
        return BASE_PATH + filename;
    }
}
