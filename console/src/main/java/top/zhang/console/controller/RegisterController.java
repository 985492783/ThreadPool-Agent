package top.zhang.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zhang.agent.entitiy.ResultMap;
import top.zhang.console.registry.RegistryCache;

import java.util.Map;

/**
 * @author 98549
 * @date 2022/10/9 22:40
 */
@Controller
public class RegisterController {

    @GetMapping("/")
    public String test(){
        return "index";
    }
    @GetMapping("homePage.html")
    public String homePage(){
        return "homePage.html";
    }
    @GetMapping("getAllThreadPoolAgent")
    @ResponseBody
    public ResultMap getAllThreadPoolAgent(@RequestParam Map<String,Object> paramMap){
        return ResultMap.ok(RegistryCache.getAll());
    }
}
