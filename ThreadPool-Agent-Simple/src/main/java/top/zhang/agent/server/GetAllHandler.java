package top.zhang.agent.server;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import top.zhang.agent.ByteBuddyAgent;
import top.zhang.agent.entitiy.HttpUtils;
import top.zhang.agent.entitiy.ResultMap;
import top.zhang.agent.wrapper.ThreadPoolWrapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 98549
 * @date 2022/10/17 16:03
 */
public class GetAllHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        IOUtils.toString(httpExchange.getRequestBody(), Charset.defaultCharset());
        Map<String, String> paramMap = HttpUtils.getParamMap(httpExchange.getRequestURI());
        String token = paramMap.get("token");
        if(!HttpUtils.assertToken(token,httpExchange)){
            return;
        }
        byte[] result = JSONObject.toJSONString(ResultMap.ok(ThreadPoolWrapper.getMap())).getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200,result.length);
        httpExchange.getResponseBody().write(result);
        httpExchange.close();
    }
}
