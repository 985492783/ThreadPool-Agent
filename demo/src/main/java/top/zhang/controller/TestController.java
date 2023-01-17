package top.zhang.controller;

import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhang.agent.wrapper.ThreadPoolWrapper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author 98549
 * @date 2022/10/6 15:16
 */
@RestController
public class TestController {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,5000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(5));
    @GetMapping("/test")
    public String setMax(@RequestParam("num") Integer num) {
        threadPoolExecutor.setMaximumPoolSize(num);
        return threadPoolExecutor.getMaximumPoolSize()+"";
    }
}
