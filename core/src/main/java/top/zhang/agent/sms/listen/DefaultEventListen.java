package top.zhang.agent.sms.listen;

import top.zhang.agent.sms.event.NotifyEvent;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:41
 */
public class DefaultEventListen implements EventListen{
    
    @Override
    public void publish(NotifyEvent notifyEvent) {
        System.out.println(notifyEvent.getMsg());
    }
}
