package top.zhang.agent.factory;

import io.netty.channel.nio.NioEventLoopGroup;
import top.zhang.agent.wrapper.NioEventLoopGroupWrapper;
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
        if(ThreadPoolExecutor.class.isAssignableFrom(executor.getClass())){
            return ThreadPoolExecutorWrapper.getInstance((ThreadPoolExecutor) executor);
            
        }else if(NioEventLoopGroup.class.isAssignableFrom(executor.getClass())){
            return NioEventLoopGroupWrapper.getInstance((NioEventLoopGroup)executor);
        }else{
            return null;
        }
    }
}
