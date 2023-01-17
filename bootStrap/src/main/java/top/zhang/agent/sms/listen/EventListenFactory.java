package top.zhang.agent.sms.listen;

import top.zhang.agent.sms.event.NotifyEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 13:36
 */
public class EventListenFactory {
    private static final List<EventListen> EVENT_LISTEN_CHAIN = new CopyOnWriteArrayList<>();
    
    public static void addListen(EventListen eventListen) {
        EVENT_LISTEN_CHAIN.add(eventListen);
        System.out.println("spi: "+eventListen.getClass().getName()+" load success");
    }
    
    public static void publishEvent(NotifyEvent notifyEvent) {
        for (EventListen eventListen : EVENT_LISTEN_CHAIN) {
            eventListen.publish(notifyEvent);
        }
    }
}
