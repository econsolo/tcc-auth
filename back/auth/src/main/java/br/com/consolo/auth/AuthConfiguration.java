package br.com.consolo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.consolo.auth.interceptor.ProtectedInterceptor;


@Configuration
public class AuthConfiguration implements WebMvcConfigurer {

	@Bean
	public ProtectedInterceptor protegidoInterceptor() {
		return new ProtectedInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(protegidoInterceptor());
	}

}
