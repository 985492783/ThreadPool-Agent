package top.zhang.agent.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import top.zhang.agent.entitiy.HttpUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author 98549
 * @date 2022/10/25 21:35
 */
public class DownHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        IOUtils.toString(httpExchange.getRequestBody(), Charset.defaultCharset());
        Map<String, String> paramMap = HttpUtils.getParamMap(httpExchange.getRequestURI());
        String token = paramMap.get("token");
        if(!HttpUtils.assertToken(token,httpExchange)){
            return;
        }
        int id=-1;
        try{
            id = Integer.parseInt(paramMap.get("id"));
        }catch (NumberFormatException e){
            e.printStackTrace();
            return;
        }

    }
}
