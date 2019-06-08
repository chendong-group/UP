package org.cd.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @classname: AuthorizationServerConfiguration
 * @description:
 * @author: Danny Chen
 * @create: 2019-06-08 12:17
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * JWT秘钥库的Bean
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * JWT Access Token转换器的Bean
     * 这里面给Converter设置了证书，并添加了自定义的一些信息到access token中
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
            //自定义一些信息access_token中
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
                final Map<String,Object> additionalInformation = new HashMap<>();
                additionalInformation.put("name", authentication.getName());
                additionalInformation.put("authorities", authentication.getAuthorities());
                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }
        };

        //秘钥工厂实例
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource(jwtProperties.getKeyStore()),
                        jwtProperties.getKeyStorePassword().toCharArray()
                );
        //设置converter秘钥对
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias()));

        return converter;
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        //这个PasswordEncoder是自定义的，为了简单起见，未设置加密和匹配，直接判断他们是否相等
        oauthServer.passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return String.valueOf(charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                if (String.valueOf(charSequence).equals(s)) {
                    return true;
                }
                return false;
            }
        }).tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
        //}).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    /**
     * 客户端配置（给谁发令牌）
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("clientId")
                .secret("secret")
                //有效时间 2小时
                .accessTokenValiditySeconds(7200)
                //密码授权模式和刷新令牌
                .authorizedGrantTypes("password");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(jwtAccessTokenConverter());
    }

}
