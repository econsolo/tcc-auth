package br.com.consolo.auth.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception {
	private static final long serialVersionUID = -4435376111033278273L;

	public UnauthorizedException() {
		super("Você não tem permissão para acessar a funcionalidade solicitada!");
	}
}
