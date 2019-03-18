package br.com.consolo.auth.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.consolo.auth.enuns.RoleEnum;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Protected {
	
	RoleEnum[] role();

}
