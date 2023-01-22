package top.zhang.agent.sms.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import top.zhang.agent.sms.annotation.SMSController;
import top.zhang.agent.sms.controller.ListenController;
import top.zhang.agent.sms.controller.SMSListenController;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 985492783@qq.com
 * @date 2023/1/22 21:57
 */
@Configuration
public class GlobalConfig implements ApplicationContextAware {
    
    private Map<String, SMSListenController> map = new ConcurrentHashMap<>();
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(SMSController.class);
        beansWithAnnotation.values().forEach((bean)->{
            SMSController smsController = bean.getClass().getAnnotation(SMSController.class);
            if (smsController == null || !(bean instanceof ListenController)) {
                return;
            }
            ListenController<?> controller = (ListenController<?>) bean;
            map.put(smsController.value(), new SMSListenController(controller, controller.getBaseUserClass()));
        });
    }
}
