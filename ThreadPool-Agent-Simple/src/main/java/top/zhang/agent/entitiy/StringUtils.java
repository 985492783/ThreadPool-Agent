package top.zhang.agent.entitiy;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author 98549
 * @date 2022/10/6 1:17
 */
public class StringUtils {
    static Pattern pattern = Pattern.compile("[0-9]*");
    static String DOT = ".";

    public static Boolean isNotEmpty(String str){
        return !isEmpty(str);
    }


    public static Boolean isEmpty(String str){
        if(Objects.isNull(str) || "".equals(str)|| "null".equals(str)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public static Boolean isNumber(String str){
        //如果是以-开头，去除
        if(str.startsWith("-")){
            str = str.replace("-","");
        }
        //判断是否有小数点
        if(str.indexOf(DOT)>0){
            //判断是否只有一个小数点
            if(str.indexOf(DOT)==str.lastIndexOf(DOT) && str.split("\\.").length==2){
                return pattern.matcher(str.replace(DOT,"")).matches();
            }
        }else {
            return pattern.matcher(str).matches();
        }
        return false;
    }
}
