package top.zhang.agent.sms.email;

import java.io.Serializable;

/**
 * @author 985492783@qq.com
 * @date 2023/1/18 0:58
 */
public class ToEmail implements Serializable {
    
    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    
    public ToEmail(String[] tos, String subject, String content) {
        this.tos = tos;
        this.subject = subject;
        this.content = content;
    }
    
    public String[] getTos() {
        return tos;
    }
    
    public void setTos(String[] tos) {
        this.tos = tos;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
}
