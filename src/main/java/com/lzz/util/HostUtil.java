package com.lzz.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by lzz on 2018/2/5.
 */
public class HostUtil {
    public static String getLocalIp() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return ip;
    }
}
