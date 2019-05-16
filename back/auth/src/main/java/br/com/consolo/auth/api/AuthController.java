package br.com.consolo.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.consolo.auth.dtos.CredentialsDTO;
import br.com.consolo.auth.dtos.TokenDTO;
import br.com.consolo.auth.services.UserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public TokenDTO login(@RequestBody CredentialsDTO credentialsDTO) {
		return userService.auth(credentialsDTO);
	}
}