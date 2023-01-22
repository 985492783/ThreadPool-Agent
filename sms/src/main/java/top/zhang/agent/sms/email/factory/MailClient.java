package top.zhang.agent.sms.email.factory;

import org.springframework.mail.javamail.JavaMailSender;
import top.zhang.agent.sms.email.MailReceive;
import top.zhang.agent.sms.email.ToEmail;
import top.zhang.agent.sms.event.NotifyEvent;
import top.zhang.agent.sms.util.MailUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author 985492783@qq.com
 * @date 2023/1/18 0:42
 */
public class MailClient {
    private boolean enabled;
    
    private Set<MailReceive> receives;
    
    private BlockingQueue<NotifyEvent> blockingQueue;
    
    private ScheduledExecutorService executorService;
    
    private JavaMailSender javaMailSender;
    
    public MailClient(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        init();
    }
    @SuppressWarnings("all")
    private void init(){
        this.receives = new CopyOnWriteArraySet<>();
        this.blockingQueue = new LinkedBlockingQueue<>();
        executorService = new ScheduledThreadPoolExecutor(2,r -> {
            Thread t = new Thread(r);
            t.setName("top.zhang.agent.sms.mail.worker");
            t.setDaemon(true);
            return t;
        });
        for(int i =0; i<2;i++){
            executorService.submit(()->{
                while (true){
                    try {
                        NotifyEvent notifyEvent = blockingQueue.take();
                        String[] rev = receives.stream().map(el -> el.getMail()).collect(Collectors.toList())
                                .toArray(new String[0]);
                        ToEmail toEmail = new ToEmail(rev, notifyEvent.getTitle(), notifyEvent.getMsg());
                        MailUtils.send(this.javaMailSender, toEmail);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
    
    public synchronized void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public synchronized void publish(NotifyEvent notifyEvent) {
        if (!enabled) {
            return;
        }
        blockingQueue.add(notifyEvent);
    }
    
    public void addReceive(List<MailReceive> receive) {
        this.receives.addAll(receive);
    }
}
