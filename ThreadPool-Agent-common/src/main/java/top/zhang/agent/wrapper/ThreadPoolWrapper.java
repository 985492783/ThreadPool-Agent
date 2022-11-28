package top.zhang.agent.wrapper;

import top.zhang.ThreadPoolMonitorData;
import top.zhang.agent.factory.ThreadPoolWrapperFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author 98549
 * @date 2022/10/7 10:58
 */
public abstract class ThreadPoolWrapper {

    protected transient static Map<Integer,ThreadPoolWrapper> map = new ConcurrentHashMap<>();

    public abstract Executor getExecutor();

    public abstract int getMaximumPoolSize();

    public abstract boolean isRunning();

    protected abstract void down();
    

    public abstract int getHashCode();

    public static void update(){
        map.values().forEach(ThreadPoolWrapper::down);
        ThreadPoolMonitorData.alls().values().forEach((monitorMap)->{
            monitorMap.values().forEach(ThreadPoolWrapperFactory::getWrapper);
        });
    }
    public static Map<Integer,ThreadPoolWrapper> getMap(){
        update();
        return map;
    }
}
