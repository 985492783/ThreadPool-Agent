package top.zhang.agent.sms.listen;

import top.zhang.agent.sms.event.NotifyEvent;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:24
 */
@FunctionalInterface
public interface EventListen {
    
    public void publish(NotifyEvent notifyEvent);
}
