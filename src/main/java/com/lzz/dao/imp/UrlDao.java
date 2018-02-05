package com.lzz.dao.imp;

import com.lzz.dao.IUrlDao;
import com.lzz.model.UrlModel;
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
        boolean res = true;
        try {
            urlMap.put(showName, urlModel);
        }catch (Exception e){
            res = false;
        }
        return res;
    }

    @Override
    public List<UrlModel> urlList() {
        List<UrlModel> urlList = new ArrayList<>();
        for(Map.Entry<String, UrlModel> element : urlMap.entrySet()){
            urlList.add( element.getValue() );
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
