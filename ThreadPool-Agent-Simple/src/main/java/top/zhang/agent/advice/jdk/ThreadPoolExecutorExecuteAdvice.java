package top.zhang.agent.advice.jdk;

import net.bytebuddy.asm.Advice;
import top.zhang.ThreadPoolMonitorData;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/6 1:22
 */
public class ThreadPoolExecutorExecuteAdvice {

    @Advice.OnMethodEnter
    public static void executeBefore(@Advice.This Object obj,@Advice.Argument(0) Object abc){
        try{
            ThreadPoolExecutor executor = (ThreadPoolExecutor)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
