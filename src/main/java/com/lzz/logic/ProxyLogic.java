package com.lzz.logic;

import com.lzz.dao.IProxyDao;
import com.lzz.model.ProxyModel;
import com.lzz.model.Response;
import com.lzz.util.NetUtil;
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


    public Response addProxy(ProxyModel proxyModel) {
        return proxyDao.add( proxyModel )  == true ? Response.Success() : Response.Fail();
    }

    public Response removeProxy(Integer proxyPort) {
        return proxyDao.remove(proxyPort)  == true ? Response.Success() : Response.Fail();
    }

    public Response proxyList() {
        List<ProxyModel> proxyList = proxyDao.proxyList();
        return new Response(0, "success", proxyList);
    }

    public boolean checkProxyPort(Integer port){
        ProxyModel proxyModel = proxyDao.getProxyPort(port);
        boolean res = false;
        if( null != proxyModel ){
            res = true;
        }else{
            res = NetUtil.checkPort( port );
        }
        return res;
    }
}
