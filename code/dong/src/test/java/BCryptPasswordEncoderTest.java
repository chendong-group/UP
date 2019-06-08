import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @classname: BCryptPasswordEncoderTest
 * @description:
 * @author: Danny Chen
 * @create: 2019-06-08 15:17
 */
public class BCryptPasswordEncoderTest {

    @Test
    public void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("test1"));
    }
}
