package com.lzz.controller;

import com.lzz.logic.UrlLogic;
import com.lzz.model.Response;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lzz on 2018/2/4.
 */
@org.springframework.stereotype.Controller
public class UrlController {
    @Resource
    UrlLogic logic;

    @RequestMapping("/south-door")
    public String postman(Model model) {
        return "south_door";
    }

    @RequestMapping(value="/add-url", method = RequestMethod.GET)
    @ResponseBody
    public Response addUrl(@RequestParam String showName, @RequestParam String url,
                           @RequestParam(name="proxyPort", defaultValue="0") int proxyPort){
        return logic.addUrl(showName, url, proxyPort);
    }

    @RequestMapping(value="/remove-url", method = RequestMethod.GET)
    @ResponseBody
    public Response removeUrl(@RequestParam String showName){
        return logic.removeUrl(showName);
    }

    @RequestMapping(value="/url-list", method = RequestMethod.GET)
    @ResponseBody
    public Response urlList(){
        return logic.urlList();
    }
}
