package br.com.consolo.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.consolo.auth.dtos.CredentialsDTO;
import br.com.consolo.auth.dtos.TokenDTO;
import br.com.consolo.auth.dtos.UserDTO;
import br.com.consolo.auth.entities.User;
import br.com.consolo.auth.exception.IncorrectEmailPasswordException;
import br.com.consolo.auth.repositories.UserRepository;
import br.com.consolo.auth.util.Util;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private Environment env;

	@Transactional(readOnly = true)
	public TokenDTO auth(CredentialsDTO credentials) {
		System.out.println(credentials.getPassword());
		System.out.println(credentials.getEmail());
		System.out.println(Util.encrypt(credentials.getPassword(), env));
		User user = userRepository.getByEmailPassword(credentials.getEmail(), Util.encrypt(credentials.getPassword(), env));

		if (user == null) {
			throw new IncorrectEmailPasswordException();
		}

		return tokenService.generateToken(new UserDTO(user));
	}
}
