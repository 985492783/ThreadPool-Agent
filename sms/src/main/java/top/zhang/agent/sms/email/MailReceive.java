package top.zhang.agent.sms.email;

import java.util.Objects;

/**
 * @author 985492783@qq.com
 * @date 2023/1/22 21:45
 */
public class MailReceive {
    private String mail;
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MailReceive that = (MailReceive) o;
        return Objects.equals(mail, that.mail);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
