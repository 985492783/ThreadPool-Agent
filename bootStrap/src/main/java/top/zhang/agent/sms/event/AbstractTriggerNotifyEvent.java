package top.zhang.agent.sms.event;

import top.zhang.agent.sms.NotifyEventEnum;

import java.util.concurrent.Executor;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:58
 */
public abstract class AbstractTriggerNotifyEvent implements NotifyEvent{
    protected NotifyEventEnum type;
    
    protected Executor executor;
    public AbstractTriggerNotifyEvent(NotifyEventEnum type, Executor executor){
        this.type = type;
        this.executor = executor;
    }
    
    @Override
    public String getMsg() {
        return "Event: [" + type.name() + "]";
    }
    
    @Override
    public Executor getExecutor() {
        return this.executor;
    }
    
    @Override
    public String getTitle() {
        return type.name();
    }
}
