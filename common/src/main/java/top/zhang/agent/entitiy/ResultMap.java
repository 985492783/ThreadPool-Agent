package top.zhang.agent.entitiy;


/**
 * @author 98549
 * @date 2022/10/19 16:30
 */

public class ResultMap<T> {
    private int code;
    private String msg;
    private T data;
    public ResultMap(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> ResultMap<T> ok(T map) {
        return new ResultMap<>(200,"成功",map);
    }

    public static  <T> ResultMap<T> fail(int code,String msg) {
        return new ResultMap<>(code,msg,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
