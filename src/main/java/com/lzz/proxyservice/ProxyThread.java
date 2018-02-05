package com.lzz.proxyservice;

import com.lzz.model.ProxyModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lzz on 2018/2/2.
 */

public class ProxyThread implements Runnable{
    private transient static Log logger = LogFactory.getLog(ProxyThread.class);
    private ProxyModel proxyModel;
    private ServerSocket serverSocket;
    private ProxySwitch threadSwitch = new ProxySwitch();

    public ProxyThread(ProxyModel proxyModel, boolean threadStart){
        this.proxyModel = proxyModel;
        this.threadSwitch.setStart( threadStart );
    }

    @Override
    public void run() {
        try {

            int proxyPort = this.proxyModel.getProxyPort();
            String remoteIp = this.proxyModel.getIp();
            int remotePort = this.proxyModel.getPort();
            serverSocket = new ServerSocket(proxyPort);

            logger.info("proxyPort="+proxyPort + ";remoteIp=" + remoteIp + ";remotePort="+remotePort);
            while( threadSwitch.isStart() == true ){
                System.out.println("while true----------");
                Socket clientSocket = null;
                Socket remoteServerSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                    logger.info("accept one client");
                    remoteServerSocket = new Socket(remoteIp ,remotePort);
                    logger.info("create remoteip and port success");
                    clientToRemote(clientSocket, remoteServerSocket);
                    remoteToClient(remoteServerSocket, clientSocket);
                } catch (Exception e) {
                    logger.error( e );
                }
            }
        } catch (Exception e) {
            logger.error( "ProxyThread", e );
        }finally {
            try {
                if( !serverSocket.isClosed() ){
                    serverSocket.close();
                }
            } catch (IOException e) {
                logger.error( e );
            }
            logger.info("stop-----------port: " + proxyModel.getProxyPort());
        }
    }

    private void clientToRemote(Socket clientSocket, Socket remoteServerSocket) {
        Thread clientToRemoteThread = new TransDataTask(clientSocket, remoteServerSocket , this.threadSwitch);
        clientToRemoteThread.setName("client to remote");
        clientToRemoteThread.start();
        logger.info( "client to remote is start......" );
    }

    private void remoteToClient(Socket remoteServerSocket, Socket clientSocket) {
        Thread remoteToClientThread = new TransDataTask(remoteServerSocket ,clientSocket, this.threadSwitch);
        remoteToClientThread.setName("remote-to-client");
        remoteToClientThread.start();
        logger.info( "remote to client is start......" );
    }

    public void stop() throws IOException {
        this.serverSocket.close();
        this.threadSwitch.setStart(false);
    }

}
