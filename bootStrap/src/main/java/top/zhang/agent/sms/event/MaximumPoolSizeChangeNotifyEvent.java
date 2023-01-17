package top.zhang.agent.sms.event;

import top.zhang.agent.sms.NotifyEventEnum;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 14:00
 */
public class MaximumPoolSizeChangeNotifyEvent extends AbstractChangeNotifyEvent<Integer>{
    
    public MaximumPoolSizeChangeNotifyEvent(Integer from, Integer to) {
        this.from = from;
        this.to = to;
        this.type = NotifyEventEnum.MAXIMUN_POOL_SIZE_CHANGE;
    }
    
}
