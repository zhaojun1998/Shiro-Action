package im.zhaojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShiroActionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroActionApplication.class, args);
    }
}