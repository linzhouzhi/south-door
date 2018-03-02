package com.lzz.dao.imp;

import com.lzz.dao.IUrlDao;
import com.lzz.model.UrlModel;
import com.lzz.util.XmlUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/2/4.
 */
@Component
public class UrlDao implements IUrlDao{
    private static final String URL_DB = "url-db.xml";
    public static Map<String, UrlModel> urlMap = new HashMap();
    static {
        for(int i = 0; i < 100; i++){
            int port = i % 2;
            UrlModel urlModel = new UrlModel("zk-manager" + i, "http://hhh.com/fff/aa", port);
            urlMap.put( urlModel.getShowName(), urlModel);
        }
    }

    @Override
    public boolean add(UrlModel urlModel) {
        String showName = urlModel.getShowName();
        XmlUtil xmlUtil = new XmlUtil( URL_DB );
        boolean res = true;
        try {
            xmlUtil.add(showName, urlModel.serializa() );
        }catch (Exception e){
            res = false;
        }
        return res;
    }

    @Override
    public List<UrlModel> urlList() {
        XmlUtil xmlUtil = new XmlUtil( URL_DB );
        List<UrlModel>  urlList = new ArrayList<>();
        Map<String, String> urlMap = xmlUtil.getAllMap();
        for(Map.Entry<String, String> element : urlMap.entrySet()){
            String value = element.getValue();
            urlList.add( UrlModel.unSerializa( value ) );
        }
        return urlList;
    }

    @Override
    public boolean delete(String showName) {
        boolean res = true;
        try {
            urlMap.remove(showName);
        }catch (Exception e){
            res = false;
        }
        return res;
    }

    @Override
    public UrlModel getUrlModel(String showName) {
        return urlMap.get(showName);
    }
}
