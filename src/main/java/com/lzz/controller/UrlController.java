package com.lzz.controller;

import com.lzz.logic.UrlLogic;
import com.lzz.model.Response;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lzz on 2018/2/4.
 */
@Controller
public class UrlController {
    @Resource
    UrlLogic logic;

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/urllist")
    public String urlList(Model model) {
        return "urllist";
    }

    @RequestMapping(value="/add-url", method = RequestMethod.GET)
    @ResponseBody
    public Response addUrl(@RequestParam String showName, @RequestParam String url,
                           @RequestParam(name="proxyPort", defaultValue="0") int proxyPort){
        return logic.addUrl(showName, url, proxyPort);
    }

    @RequestMapping(value="/save-url", method = RequestMethod.POST)
    @ResponseBody
    public Response saveUrl(@RequestBody JSONObject req){
        return logic.saveUrl(req);
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
