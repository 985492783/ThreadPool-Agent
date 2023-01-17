package top.zhang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 98549
 * @date 2022/10/6 14:43
 */
public class ThreadPoolMonitorData {

    private static Map<Class<?> , Map<Integer, Executor>> map = new ConcurrentHashMap<>();
    private static Map<Integer, AtomicInteger> rejectMap = new ConcurrentHashMap<>();
    public static boolean add(Executor obj) {
        Class<? extends Executor> key = obj.getClass();
        Map<Integer, Executor> threadPoolExecutorMap = map.get(key);
        if(threadPoolExecutorMap==null){
            threadPoolExecutorMap = new ConcurrentHashMap<>();
            map.put(key,threadPoolExecutorMap);
        }
        if(!threadPoolExecutorMap.containsKey(obj.hashCode())){
            register(threadPoolExecutorMap,obj);
            return true;
        }
        return false;
    }
    private static void register(Map<Integer,Executor> map,Executor obj){
        map.put(obj.hashCode(),obj);
        rejectMap.put(obj.hashCode(),new AtomicInteger(0));
    }

    public static void reject(Executor obj){
        AtomicInteger rejectCount = rejectMap.get(obj.hashCode());
        rejectCount.incrementAndGet();
    }
    public static AtomicInteger getReject(Executor executor){
        return rejectMap.get(executor.hashCode());
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
