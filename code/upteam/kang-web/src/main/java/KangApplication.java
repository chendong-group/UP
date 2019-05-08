import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @classname: UpteamApplication
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-08 13:42
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.upteam"})
public class KangApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangApplication.class, args);
    }
}
