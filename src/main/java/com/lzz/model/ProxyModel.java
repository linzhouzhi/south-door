package com.lzz.model;

/**
 * Created by lzz on 2018/2/4.
 */
public class ProxyModel {
    private int proxyPort;
    private int port;
    private String ip;

    public ProxyModel(){

    }

    public ProxyModel(int proxyPort, int port, String ip) {
        this.proxyPort = proxyPort;
        this.port = port;
        this.ip = ip;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
