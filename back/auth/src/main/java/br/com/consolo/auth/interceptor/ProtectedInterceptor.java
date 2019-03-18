package br.com.consolo.auth.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.consolo.auth.dtos.RoleDTO;
import br.com.consolo.auth.dtos.TokenDTO;
import br.com.consolo.auth.enuns.RoleEnum;
import br.com.consolo.auth.exception.UnauthorizedException;
import br.com.consolo.auth.exception.ExpiratedSessionException;
import br.com.consolo.auth.exception.InvalidCredentialsException;
import br.com.consolo.auth.services.TokenService;

public class ProtectedInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenService tokenService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            
            Protected annotation = handlerMethod.getMethod().getAnnotation(Protected.class);
            
            if (annotation != null) {
            	
            	String token = request.getHeader("token");
            	
            	if (StringUtils.isEmpty(token)) {
            		throw new UnauthorizedException();
            	}
            	
            	TokenDTO tokenDTO = tokenService.getPorToken(token);
            	
            	if (tokenDTO == null) {
            		throw new InvalidCredentialsException();
            	}
            		
            	if (tokenService.isTokenExpirada(tokenDTO)) {
            		throw new ExpiratedSessionException();
            	}
            	
            	RoleDTO roleDTO = tokenDTO.getUserDTO().getRoleDTO();
            	
            	if (!RoleEnum.ADMIN.getId().equals(roleDTO.getId())) {
	            	
	            	boolean perfilTemAcesso = Arrays.asList(annotation.role()).stream()
	            			.anyMatch(p -> roleDTO.getId().equals(p.getId()));
	            	
	            	if (!perfilTemAcesso) {
	            		throw new UnauthorizedException();
	            	}
            	}
            }
            
        }
        return true;
    }
	
}
