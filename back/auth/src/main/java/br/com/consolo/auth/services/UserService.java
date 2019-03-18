package br.com.consolo.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.consolo.auth.dtos.CredentialsDTO;
import br.com.consolo.auth.dtos.UserDTO;
import br.com.consolo.auth.entities.User;
import br.com.consolo.auth.repositories.UserRepository;

@Service
//@Scope("prototype")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public UserDTO getByCredentials(CredentialsDTO credentialsDTO) {
		
		User user = userRepository.getByEmailPassword(credentialsDTO.getEmail(), credentialsDTO.getPassword());
		
		if (user == null) {
			throw new RuntimeException("Email ou senha está incorreto!");
		}
		
		return new UserDTO(user);
		
	}
}
