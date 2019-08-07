package com.iktpreobuka.elektronskidnevnik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

	private static String REALM="MY_OAUTH_REALM";
	static final String CLIENT_ID = "admin";
	static final String CLIENT_SECRET = "ednevnik-secret";
	static final String GRANT_TYPE = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private UserApprovalHandler userApprovalHandler;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory()
		.withClient(CLIENT_ID)
		.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
		.authorities("ROLE_ADMIN")
		.scopes("read", "write", "trust")
		.secret(CLIENT_SECRET)
		.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
		.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}
	
	@Override 
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) 
			throws Exception { 
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
		.authenticationManager(authenticationManager); 
		}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
		oauthServer.realm(REALM+"/client");
	}
}
