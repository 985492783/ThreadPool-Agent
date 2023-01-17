package top.zhang.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.zhang.console.registry.RegistryCache;

/**
 * @author 98549
 * @date 2022/10/9 22:36
 */
@SpringBootApplication
public class ConsoleMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsoleMain.class);
        RegistryCache.intervalUpdate();
    }
}
