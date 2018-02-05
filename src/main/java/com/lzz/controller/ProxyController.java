package com.lzz.controller;

import com.lzz.logic.ProxyLogic;
import com.lzz.model.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lzz on 2018/2/4.
 */
@org.springframework.stereotype.Controller
public class ProxyController {
    @Resource
    ProxyLogic logic;

    @RequestMapping(value="/add-proxy", method = RequestMethod.GET)
    @ResponseBody
    public Response addProxy(@RequestParam int proxyPort, @RequestParam int port, @RequestParam String ip){
        return logic.addProxy(proxyPort, port, ip);
    }

    @RequestMapping(value="/remove-proxy", method = RequestMethod.GET)
    @ResponseBody
    public Response removeProxy(@RequestParam int proxyPort){
        return logic.removeProxy(proxyPort);
    }

    @RequestMapping(value="/proxy-list", method = RequestMethod.GET)
    @ResponseBody
    public Response proxyList(){
        return logic.proxyList();
    }
}
