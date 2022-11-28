package top.zhang.agent.advice.netty;

import io.netty.util.concurrent.EventExecutorGroup;
import net.bytebuddy.asm.Advice;
import top.zhang.ThreadPoolMonitorData;

/**
 * @author 98549
 * @date 2022/10/7 0:25
 */
public class NioEventLoopGroupConstructorAdvice {

    @Advice.OnMethodExit
    public static void constructor(@Advice.This Object obj, @Advice.AllArguments Object[] args){
        try {
            if(obj!=null){
                EventExecutorGroup executor = (EventExecutorGroup) obj;
                ThreadPoolMonitorData.add(executor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
