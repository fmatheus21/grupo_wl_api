package com.firecode.app.config;

import com.firecode.app.controller.token.CustomTokenEnhancer;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/* Classe de autorizacao da aplicacao que ira consumir a API */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Value("${api.token.validity.seconds}")
    private int tokenValiditySeconds;  

    @Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {      
        clients.inMemory()
                .withClient("angular")
                .secret(passwordEncoder.encode("angular210683@"))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(tokenValiditySeconds) // 1800/60 = 30 minutos
                .refreshTokenValiditySeconds(3600 * 24) // 3600*24 = 24h
                .and()
                .withClient("reactnative")
                .secret(passwordEncoder.encode("angular210683@"))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(tokenValiditySeconds) // 1800/60 = 30 minutos
                .refreshTokenValiditySeconds(3600 * 24); // 3600*24 = 24h
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Arrays.asList(this.tokenEnhancer(), this.accessTokenConverter()));
        endpoints
                .tokenStore(this.tokenStore())
                .tokenEnhancer(chain)
                .accessTokenConverter(this.accessTokenConverter())
                .reuseRefreshTokens(false)
                .authenticationManager(authenticationManager);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("firecode210683@");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {       
        return new CustomTokenEnhancer();
    }

}
