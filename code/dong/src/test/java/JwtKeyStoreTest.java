import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @classname: JwtKeyStoreTest
 * @description:
 * @author: Danny Chen
 * @create: 2019-06-08 11:53
 */
public class JwtKeyStoreTest {

    @Test
    public void test() throws Exception {
        PrivateKey privateKey = getPrivateKey("jwt.jks", "123456", "jwt");
        System.out.println("jwt.jks PrivateKey is: ");
        System.out.println(privateKey);

        PublicKey publicKey = getPublicKey("jwt.jks", "123456", "jwt");
        System.out.println("jwt.jks PublicKey is: ");
        System.out.println(publicKey);
    }

    private static PrivateKey getPrivateKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());

    }

    private static PublicKey getPublicKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return keyStore.getCertificate("jwt").getPublicKey();

    }

}
