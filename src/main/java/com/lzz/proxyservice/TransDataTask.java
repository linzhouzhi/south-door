package com.lzz.proxyservice;

/**
 * Created by lzz on 2017/9/11.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class TransDataTask extends Thread {
    private transient static Log log = LogFactory.getLog(ProxyManager.class);
    Socket getDataSocket;
    Socket putDataSocket;
    ProxySwitch threadSwitch;
    public TransDataTask(Socket getDataSocket, Socket putDataSocket, ProxySwitch threadSwitch){
        this.getDataSocket = getDataSocket;
        this.putDataSocket = putDataSocket;
        this.threadSwitch = threadSwitch;
    }
    public void run(){
        try {
            while ( this.threadSwitch.isStart() == true ){
                try {
                    System.out.println("transportdata----" + this.threadSwitch.isStart());
                    InputStream in = getDataSocket.getInputStream() ;
                    OutputStream out = putDataSocket.getOutputStream() ;

                    byte[] data = new byte[1024];
                    int readlen = in.read(data);

                    if(readlen<=0){ // 没有数据就直接退出
                        Thread.sleep(1000);
                        System.out.println("stop trans data break.....");
                        break;
                    }
                    out.write(data ,0,readlen);
                    out.flush();
                }catch (Exception ignore){}
            }
        } catch (Exception e) {
            log.error( e );
        }finally {
            System.out.println("transedata port data ------- finally");
            try {
                if( putDataSocket.isOutputShutdown() == false ){
                    putDataSocket.shutdownOutput();
                }
                if(getDataSocket.isInputShutdown() == false ){
                    getDataSocket.shutdownInput();
                }
            }catch (Exception e){
                log.error( e );
            }
        }
    }

}