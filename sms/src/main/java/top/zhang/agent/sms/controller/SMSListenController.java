package top.zhang.agent.sms.controller;

/**
 * @author 985492783@qq.com
 * @date 2023/1/23 0:09
 */
public class SMSListenController {
    private ListenController<?> listenController;
    private Class<?> aClass;
    
    public SMSListenController(ListenController<?> listenController, Class<?> aClass) {
        this.listenController = listenController;
        this.aClass = aClass;
    }
    
    public ListenController<?> getListenController() {
        return listenController;
    }
    
    public void setListenController(ListenController<?> listenController) {
        this.listenController = listenController;
    }
    
    public Class<?> getaClass() {
        return aClass;
    }
    
    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }
}
