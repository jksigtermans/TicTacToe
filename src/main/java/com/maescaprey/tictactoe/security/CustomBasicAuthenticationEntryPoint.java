package com.maescaprey.tictactoe.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * The actual Entry point, which will get triggered if authentication failed.
 * 
 * @see {@link BasicAuthenticationEntryPoint}.
 * 
 * @author Maes-Caprey
 */
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
    public void commence(final HttpServletRequest request, 
    		final HttpServletResponse response, 
    		final AuthenticationException authException) throws IOException {

		// Authentication failed, send error response
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

		final PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 : " + authException.getMessage());
	}

	@Override
    public void afterPropertiesSet() {

		setRealmName(CustomWebSecurityConfigurerAdapter.REALM);
		super.afterPropertiesSet();
	}
}