package root.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizeServerConfig extends AuthorizationServerConfigurerAdapter {
   private String clientid = "vin-study-client";
   private String clientSecret = "vin-secret-key";
   private String privateKey = "vin-private-key";
   private String publicKey = "vin-public-key";

   @Autowired
   @Qualifier("authenticationManagerBean")
   private AuthenticationManager authenticationManager;
   
   @Bean
   public JwtAccessTokenConverter tokenEnhancer() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      //converter.setSigningKey(privateKey);
      //converter.setVerifierKey(publicKey);
      converter.setSigningKey("secret");
      return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() {
      return new JwtTokenStore(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
      	.authenticationManager(authenticationManager)
      	.tokenStore(tokenStore())
      	.accessTokenConverter(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security
        .tokenKeyAccess("permitAll()")
      	.checkTokenAccess("isAuthenticated()");
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory()
      	.withClient(clientid)
      	.secret(clientSecret)
      	.scopes("read", "write")
        .authorizedGrantTypes("password", "refresh_token")  //"authorization_code" //"client_credentials" //"implicit"
        .accessTokenValiditySeconds(20000)
        .refreshTokenValiditySeconds(20000);      
   }
} 




//==================Available Grant Types=======================
//1. implicit	             //- Implicit Grant
//2. authorization_code    //- Authorization Code Grant
//3. client_credentials    //- Client Credentials Grant
//4. password	             //- Resource Owner Password Grant
//5. refresh_token	     //- Use Refresh Tokens
//6. urn:ietf:params:oauth:grant-type:device_code