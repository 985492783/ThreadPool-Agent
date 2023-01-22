package top.zhang.agent.sms.email.listen;

import org.springframework.beans.factory.annotation.Autowired;
import top.zhang.agent.entitiy.ResultMap;
import top.zhang.agent.sms.annotation.SMSController;
import top.zhang.agent.sms.controller.ListenController;
import top.zhang.agent.sms.email.MailReceive;
import top.zhang.agent.sms.email.factory.MailClient;
import top.zhang.agent.sms.event.NotifyEvent;
import top.zhang.agent.sms.listen.EventListen;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/1/17 23:14
 */
@SMSController(value = "邮件警告")
public class MailEventListen implements EventListen, ListenController<MailReceive> {
    
    @Autowired
    private MailClient mailClient;
    
    @Override
    public void publish(NotifyEvent notifyEvent) {
        mailClient.publish(notifyEvent);
    }
    
    
    @Override
    public Boolean openListen() {
        mailClient.setEnabled(true);
        return true;
    }
    
    @Override
    public Boolean closeListen() {
        mailClient.setEnabled(false);
        return true;
    }
    
    @Override
    public Boolean addReceive(List<MailReceive> receive) {
        mailClient.addReceive(receive);
        return true;
    }
}
