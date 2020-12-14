package com.maescaprey.tictactoe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * HTTP Basic authentication configuration.  
 * 
 * @author Maes-Caprey
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	/*
	 * REALM Name for this application.
	 */
	public static String REALM = "GAME_REALM";

//TODO: remove predefined credentials
    @Autowired
    public void configureGlobalSecurity(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.inMemoryAuthentication().withUser("foo").password("bar").roles("USER");
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
  
      httpSecurity.csrf().disable()
        .authorizeRequests()
        .antMatchers("/srv/**").hasRole("USER")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //We don't need sessions to be created
    }
    
    /**
     * Allow both registering of a new player, and pre-flight [OPTIONS] request from browser.
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {

    	web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/srv/register_player/**");
    }

    /**
     * Return custom Basic Authentication entry point. 
     *
     * @see {@link CustomBasicAuthenticationEntryPoint}.
     * 
     * @return Custom Basic Authentication entry point. 
     */
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
    	
        return new CustomBasicAuthenticationEntryPoint();
    }
    
    /**
     * Return mandatory password encoder.
     * Our custom password encoder does a basic equality check on given and stored password.
     * 
     * @return Custom password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
    	
        return new PasswordEncoder() {

			@Override
			public String encode(final CharSequence rawPassword) {

				return String.valueOf(rawPassword);
			}

			@Override
			public boolean matches(final CharSequence rawPassword, final String encodedPassword) {

				return rawPassword != null && encodedPassword != null && encodedPassword.contentEquals(rawPassword);
			}
        };
    }
}