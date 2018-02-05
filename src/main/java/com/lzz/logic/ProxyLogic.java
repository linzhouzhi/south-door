package com.lzz.logic;

import com.lzz.dao.IProxyDao;
import com.lzz.model.ProxyModel;
import com.lzz.model.Response;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lzz on 2018/2/4.
 */
@Component
public class ProxyLogic {
    @Resource
    IProxyDao proxyDao;


    public Response addProxy(int proxyPort, int port, String ip) {
        ProxyModel proxyModel = new ProxyModel(proxyPort, port, ip);
        return proxyDao.add( proxyModel )  == true ? Response.Success() : Response.Fail();
    }

    public Response removeProxy(int proxyPort) {
        return proxyDao.remove(proxyPort)  == true ? Response.Success() : Response.Fail();
    }

    public Response proxyList() {
        List<ProxyModel> proxyList = proxyDao.proxyList();
        return new Response(0, "success", proxyList);
    }
}
