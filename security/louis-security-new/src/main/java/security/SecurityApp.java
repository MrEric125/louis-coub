package security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.liu
 * @since 2021/5/19 18:06
 */
@RestController
@SpringBootApplication
public class SecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
    }

    @RequestMapping("/ok")
    public String login() {
        return "login";
    }

}
