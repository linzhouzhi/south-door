package com.lzz.dao.imp;

import com.lzz.dao.IProxyDao;
import com.lzz.model.ProxyModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lzz on 2018/2/4.
 */
@Component
public class ProxyDao implements IProxyDao {
    public static Map<Integer, ProxyModel> proxyMap = new ConcurrentHashMap<>();
    static {
        ProxyModel proxyModel1 = new ProxyModel(8071, 8081, "127.0.0.1");
        ProxyModel proxyModel2 = new ProxyModel(8072, 8081, "127.0.0.1");
        proxyMap.put(proxyModel1.getProxyPort(), proxyModel1);
        proxyMap.put(proxyModel2.getProxyPort(), proxyModel2);
    }

    public static Map<Integer, ProxyModel> getProxyMeta(){
        return proxyMap;
    }

    @Override
    public boolean add(ProxyModel proxyModel) {
        boolean res = true;
        try{
           proxyMap.put(proxyModel.getProxyPort(), proxyModel);
        }catch (Exception e){
            res = false;
        }
        return res;
    }

    @Override
    public boolean remove(int proxyPort) {
        boolean res = true;
        try{
            proxyMap.remove(proxyPort);
        }catch (Exception e){
            res = false;
        }
        return res;
    }

    @Override
    public List<ProxyModel> proxyList() {
        List<ProxyModel> proxyModelList = new ArrayList<>();
        for(Map.Entry<Integer, ProxyModel> element : proxyMap.entrySet()){
            proxyModelList.add( element.getValue() );
        }
        return proxyModelList;
    }

    @Override
    public ProxyModel getProxyPort(Integer port) {
        return proxyMap.get( port );
    }
}
