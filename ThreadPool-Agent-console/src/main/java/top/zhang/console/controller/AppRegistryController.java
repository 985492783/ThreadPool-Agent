package top.zhang.console.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhang.agent.entitiy.ResultMap;
import top.zhang.console.registry.RegistryCache;

import java.util.Map;

/**
 * @author 98549
 * @date 2022/10/19 16:56
 */
@RestController
public class AppRegistryController {

    @GetMapping("/register")
    public ResultMap register(@RequestParam Map<String,String> param){
        return RegistryCache.register(param);
    }
}
