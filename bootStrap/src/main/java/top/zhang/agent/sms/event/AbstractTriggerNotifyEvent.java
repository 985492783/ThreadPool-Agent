package top.zhang.agent.sms.event;

import top.zhang.agent.sms.NotifyEventEnum;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:58
 */
public abstract class AbstractTriggerNotifyEvent implements NotifyEvent{
    protected NotifyEventEnum type;
    
    @Override
    public String getMsg() {
        return "Event: [" + type.name() + "]";
    }
}
