package top.zhang.agent.entitiy;

import com.sun.net.httpserver.HttpExchange;
import top.zhang.agent.ByteBuddyAgent;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 98549
 * @date 2022/10/17 16:20
 */
public class HttpUtils {
    public static Map<String,String> getParamMap(URI uri){
        Map<String,String> paramMap = new HashMap<>();
        String query = uri.getQuery();
        String[] split = query.split("&");
        Arrays.stream(split).forEach((q)->{
            String[] param = q.split("=");
            if(param.length==2){
                paramMap.put(param[0],param[1]);
            }
        });
        return paramMap;
    }

    public static boolean assertToken(String token, HttpExchange httpExchange) throws IOException {
        if(!token.equals(ByteBuddyAgent.getToken())){
            byte[] bytes = "token is Incorrect".getBytes(StandardCharsets.UTF_8);
            httpExchange.sendResponseHeaders(400,bytes.length);
            httpExchange.getResponseBody().write(bytes);
            httpExchange.close();
            return false;
        }
        return true;
    }
}
