package top.zhang.console.utils;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import top.zhang.agent.entitiy.ResultMap;
import top.zhang.console.entity.Registry;

import java.io.IOException;

/**
 * @author 98549
 * @date 2022/10/19 16:30
 */
public class HttpUtils {
    private static HttpClient httpClient = HttpClients.createDefault();
    public static ResultMap update(Registry registry){
        HttpGet httpGet = new HttpGet("http://" + registry.getIp() + ":" + registry.getPort() + "/getAll?token=" + registry.getToken());
        ResultMap resultMap=null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            resultMap = JSONObject.parseObject(EntityUtils.toString(response.getEntity()), ResultMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
