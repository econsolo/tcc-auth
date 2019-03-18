package br.com.consolo.auth.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidCredentialsException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Credenciais inválidas! Efetue a autenticação novamente.");
	}
}
