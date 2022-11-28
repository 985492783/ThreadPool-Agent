package top.zhang.console.registry;

import top.zhang.agent.entitiy.ResultMap;
import top.zhang.agent.wrapper.ThreadPoolWrapper;
import top.zhang.console.entity.Registry;
import top.zhang.console.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 98549
 * @date 2022/10/19 16:26
 */
public class RegistryCache {
    private static Map<Registry,Map<Integer, ThreadPoolWrapper>> registryMap= new HashMap<>();

    public static void intervalUpdate(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                registryMap.keySet().forEach((k)->{
                    ResultMap resultMap = HttpUtils.update(k);
                    if(resultMap!=null && resultMap.getCode()==200){
                        registryMap.put(k,(Map<Integer, ThreadPoolWrapper>) resultMap.getData());
                    }
                });
            }
        },1000,1000);
    }
    public static boolean register(String ip,int port,String token){
        Registry registry = new Registry(ip, port, token);
        if(registryMap.containsKey(registry)){
            return false;
        }
        registryMap.put(registry,null);
        return true;
    }

    public static ResultMap register(Map<String, String> param) {
        boolean success = register(param.get("ip"), Integer.parseInt(param.get("port")), param.get("token"));
        if(success){
            return ResultMap.ok(null);
        }
        return ResultMap.fail(201,"已经存在");
    }

    public static Map<Registry,Map<Integer, ThreadPoolWrapper>> getAll() {
        return registryMap;
    }
}
