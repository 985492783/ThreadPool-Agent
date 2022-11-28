package top.zhang.agent.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author 98549
 * @date 2022/10/17 16:00
 */
public class MyHttpServer {
    private HttpServer httpServer;

    public void bind(int port){
        try{
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/getAll",new GetAllHandler());
            httpServer.createContext("/down",new DownHandler());
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void print(){
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress()+":" +httpServer.getAddress().getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
