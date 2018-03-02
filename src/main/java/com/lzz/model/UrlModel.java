package com.lzz.model;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by lzz on 2018/2/4.
 */
@Component
public class UrlModel {
    private String showName;
    private String url;
    private int proxyPort;


    public UrlModel(){

    }
    public UrlModel(String showName, String url, int proxyPort) {
        this.showName = showName;
        this.url = url;
        this.proxyPort = proxyPort;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String serializa(){
        return JSONObject.fromObject(this).toString();
    }

    public static UrlModel unSerializa(String str){
        JSONObject jsonObject =  JSONObject.fromObject( str );
        UrlModel urlModel = (UrlModel) JSONObject.toBean( jsonObject, UrlModel.class);
        return  urlModel;
    }
}
