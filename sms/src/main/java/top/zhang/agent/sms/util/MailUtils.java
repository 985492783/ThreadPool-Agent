package top.zhang.agent.sms.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import top.zhang.agent.sms.email.ToEmail;

/**
 * @author 985492783@qq.com
 * @date 2023/1/18 0:57
 */
public class MailUtils {
    private static String from = "985492783@qq.com";
    
    public static void send(JavaMailSender javaMailSender, ToEmail toEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail.getTos());
        message.setSubject(toEmail.getSubject());
        message.setText(toEmail.getContent());
        javaMailSender.send(message);
    }
}
