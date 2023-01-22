package top.zhang.agent.advice.jdk;

import net.bytebuddy.asm.Advice;
import top.zhang.ThreadPoolMonitorData;
import top.zhang.agent.sms.event.MaximumPoolSizeChangeNotifyEvent;
import top.zhang.agent.sms.listen.EventListenFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 12:38
 */
public class ThreadPoolExecutorSetMaximumPoolSize {
    
    @Advice.OnMethodEnter
    public static void setMaximumPoolSize(@Advice.This Object obj, @Advice.Argument(0) Object abc){
        try{
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) obj;
            Integer maximumPoolSize = (Integer) abc;
            if (maximumPoolSize <= 0 || maximumPoolSize < threadPoolExecutor.getCorePoolSize()) {
                //TODO 此处有一个trigger事件
                return;
            }
            ThreadPoolMonitorData.maximumPoolSizeChange(threadPoolExecutor.getMaximumPoolSize(), maximumPoolSize, threadPoolExecutor);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
