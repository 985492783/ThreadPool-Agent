package top.zhang.agent.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import top.zhang.agent.sms.email.factory.MailClient;
import top.zhang.agent.sms.email.listen.MailEventListen;
import top.zhang.agent.sms.listen.EventListen;
import top.zhang.agent.sms.listen.EventListenFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 985492783@qq.com
 * @date 2023/1/18 0:15
 */
@EnableConfigurationProperties(value = {MailAutoConfiguration.MailConfigurationProperties.class})

public class MailAutoConfiguration {
    
    private static Map<Class<?> , Object> beanMap = new ConcurrentHashMap<>();
    
    @Bean
    public JavaMailSender getJavaMailSender(MailConfigurationProperties properties){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(properties.host);
        javaMailSender.setUsername(properties.username);
        javaMailSender.setPassword(properties.password);
        javaMailSender.setPort(properties.port);
        javaMailSender.setDefaultEncoding("utf-8");
        javaMailSender.setProtocol("smtps");
        beanMap.put(JavaMailSender.class, javaMailSender);
        return javaMailSender;
    }
    
    @Bean
    public MailClient getMailClient(MailConfigurationProperties properties, JavaMailSender javaMailSender){
        MailClient mailClient = new MailClient(javaMailSender);
        mailClient.setEnabled(properties.enabled);
        beanMap.put(MailClient.class, mailClient);
        return mailClient;
    }
    @Bean
    public MailEventListen getMailEventListen(){
        return EventListenFactory.getEventListen(MailEventListen.class);
    }
    public static Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }
    @ConfigurationProperties(prefix = "sms.mail")
    static class MailConfigurationProperties{
        private boolean enabled;
        
        private int port;
        
        private String host;
        
        private String username;
        
        private String password;
    
        public boolean isEnabled() {
            return enabled;
        }
    
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    
        public int getPort() {
            return port;
        }
    
        public void setPort(int port) {
            this.port = port;
        }
    
        public String getHost() {
            return host;
        }
    
        public void setHost(String host) {
            this.host = host;
        }
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
