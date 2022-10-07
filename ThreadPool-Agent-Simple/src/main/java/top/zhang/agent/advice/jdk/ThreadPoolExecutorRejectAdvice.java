package top.zhang.agent.advice.jdk;

import net.bytebuddy.asm.Advice;
import top.zhang.ThreadPoolMonitorData;
import top.zhang.ThreadPoolWrapperFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/7 11:20
 */
public class ThreadPoolExecutorRejectAdvice {

    @Advice.OnMethodExit
    public static void rejectAfter(@Advice.This Object obj,@Advice.Argument(0) Object abc){
        try{
            //以下代码不能抽取，一旦抽取，必须用bootstrap加载器加载
            ThreadPoolExecutor executor = (ThreadPoolExecutor)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
