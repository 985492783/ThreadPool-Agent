package top.zhang.controller;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhang.ThreadPoolMonitorData;


/**
 * @author 98549
 * @date 2022/10/6 15:16
 */
@RestController
public class TestController {

    @GetMapping("/")
    public String test(){
        return JSONObject.toJSONString(ThreadPoolMonitorData.alls());
    }
    @GetMapping("/test")
    public String HH(){
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        return "success";
    }
}
