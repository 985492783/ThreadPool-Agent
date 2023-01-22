package top.zhang.agent.sms.event;

import top.zhang.agent.sms.NotifyEventEnum;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:25
 */
public interface NotifyEvent {
    /**
     * 获取通知信息.
     */
    public String getMsg();
    
    public Executor getExecutor();
    
    public String getTitle();
}
