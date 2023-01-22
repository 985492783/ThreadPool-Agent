package top.zhang.agent.sms.controller;

import org.springframework.web.bind.annotation.RequestBody;
import top.zhang.agent.entitiy.ResultMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/1/22 20:11
 */
public interface ListenController<T> {
    public Boolean openListen();
    
    public Boolean closeListen();
    
    public Boolean addReceive(@RequestBody List<T> receive);
    
    default Class<?> getBaseUserClass(){
        for (Type type : getClass().getGenericInterfaces()) {
            if(type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?> clazz = (Class<?>) parameterizedType.getRawType();
                if(clazz.equals(ListenController.class)){
                    return (Class<?>) parameterizedType.getActualTypeArguments()[0];
                }
            }
        }
        return null;
    }
}
