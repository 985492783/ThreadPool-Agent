package top.zhang.agent.sms.listen;

import top.zhang.agent.sms.event.NotifyEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:36
 */
public class EventListenFactory {
    private static final Map<Class<?>, EventListen> EVENT_LISTEN_CHAIN = new ConcurrentHashMap<>();
    
    public static void addListen(EventListen eventListen) {
        EVENT_LISTEN_CHAIN.put(eventListen.getClass(), eventListen);
        System.out.println("spi: "+eventListen.getClass().getName()+" load success");
    }
    
    public static void publishEvent(NotifyEvent notifyEvent) {
        for (EventListen eventListen : EVENT_LISTEN_CHAIN.values()) {
            eventListen.publish(notifyEvent);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends EventListen> T getEventListen(Class<T> className) {
        return (T)EVENT_LISTEN_CHAIN.get(className);
    }
}
