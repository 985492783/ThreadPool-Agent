package top.zhang;


import top.zhang.wrapper.ThreadPoolWrapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author 98549
 * @date 2022/10/6 14:43
 */
public class ThreadPoolMonitorData {

    private static Map<Class<?> , Map<Integer, Executor>> map = new ConcurrentHashMap<>();

    public static boolean add(Executor obj) {
        Class<? extends Executor> key = obj.getClass();
        Map<Integer, Executor> threadPoolExecutorMap = map.get(key);
        if(threadPoolExecutorMap==null){
            threadPoolExecutorMap = new ConcurrentHashMap<>();
            map.put(key,threadPoolExecutorMap);
        }
        if(!threadPoolExecutorMap.containsKey(obj.hashCode())){
            threadPoolExecutorMap.put(obj.hashCode(),obj);
            return true;
        }
        return false;
    }

    public static void remove(Executor obj) {
        Class<? extends Executor> key = obj.getClass();
        Map<Integer, Executor> threadPoolExecutorMap = map.get(key);
        if(threadPoolExecutorMap!=null){
            threadPoolExecutorMap.remove(obj.hashCode());
        }
    }


    public static Map<Class<?> , Map<Integer, Executor>> alls() {
        return map;
    }
}
