package top.zhang.agent.sms.event;

import top.zhang.agent.sms.NotifyEventEnum;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:15
 */
public abstract class AbstractChangeNotifyEvent<T> implements NotifyEvent {
    protected NotifyEventEnum type;
    
    protected T from;
    
    protected T to;
    
    @Override
    public String getMsg() {
        return "Event: [" + type.name()+" from " + from + " to " + to + "]";
    }
}
