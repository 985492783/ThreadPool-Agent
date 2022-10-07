package top.zhang.agent.advice.jdk;

import net.bytebuddy.asm.Advice;
import top.zhang.ThreadPoolMonitorData;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/6 1:20
 */

public class ThreadPoolExecutorConstructorAdvice {
    @Advice.OnMethodExit
    public static void constructor(@Advice.This Object obj, @Advice.AllArguments Object[] args){
        try {
            if(obj!=null){
                ThreadPoolExecutor executor = (ThreadPoolExecutor)obj;
                ThreadPoolMonitorData.add(executor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
