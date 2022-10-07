package top.zhang.agent.advice.jdk;

import net.bytebuddy.asm.Advice;
import top.zhang.ThreadPoolMonitorData;
import top.zhang.ThreadPoolWrapperFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/6 1:20
 */
public class ThreadPoolExecutorDestroyAdvice {
    @Advice.OnMethodEnter
    public static void finalize(@Advice.This Object obj){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) obj;
        ThreadPoolMonitorData.remove(executor);
    }

}
