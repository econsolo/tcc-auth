package br.com.consolo.auth.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiratedSessionException extends RuntimeException {
	private static final long serialVersionUID = -4435376111033278273L;

	public ExpiratedSessionException() {
		super("Sessão expirou! Efetue a autenticação novamente.");
	}
}
