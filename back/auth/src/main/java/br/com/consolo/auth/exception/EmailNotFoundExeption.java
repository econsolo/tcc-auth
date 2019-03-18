package br.com.consolo.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailNotFoundExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmailNotFoundExeption() {
		super("E-mail não encontrado");
	}
}
