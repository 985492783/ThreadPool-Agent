package top.zhang.agent.sms.event;

import top.zhang.agent.sms.NotifyEventEnum;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:15
 */
public abstract class AbstractChangeNotifyEvent<T> implements NotifyEvent {
    protected NotifyEventEnum type;
    
    protected T from;
    
    protected T to;
    
    protected Executor executor;
    
    public AbstractChangeNotifyEvent(NotifyEventEnum type, T from, T to, Executor executor) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.executor = executor;
    }
    @Override
    public String getMsg() {
        return "Event: [" + type.name()+" from " + from + " to " + to + "]";
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
