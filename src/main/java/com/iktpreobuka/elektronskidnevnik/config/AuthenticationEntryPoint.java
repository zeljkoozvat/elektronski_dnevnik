package com.iktpreobuka.elektronskidnevnik.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint{
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("DeveloperStack");
		super.afterPropertiesSet();
	}
	@Override public void commence(HttpServletRequest request, 
			HttpServletResponse response, AuthenticationException authEx) 
					throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
		PrintWriter writer = response.getWriter(); 
		writer.println("HTTP Status 401 - " + authEx.getMessage());
		
		logger.error("Unauthorized attempt");
		}
}
