package top.zhang.agent.factory;

import top.zhang.agent.wrapper.ThreadPoolExecutorWrapper;
import top.zhang.agent.wrapper.ThreadPoolWrapper;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/7 11:26
 */
public class ThreadPoolWrapperFactory {
    public static ThreadPoolWrapper getWrapper(Executor executor){
        return ThreadPoolExecutorWrapper.getInstance((ThreadPoolExecutor) executor);
    }
}
