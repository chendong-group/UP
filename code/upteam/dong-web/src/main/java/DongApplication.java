import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @classname: UpteamApplication
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-08 13:42
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DongApplication {
    public static void main(String[] args) {
        SpringApplication.run(DongApplication.class, args);
    }
}
