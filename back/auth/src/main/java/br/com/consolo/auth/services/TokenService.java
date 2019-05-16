package br.com.consolo.auth.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.consolo.auth.dtos.TokenDTO;
import br.com.consolo.auth.dtos.UserDTO;
import br.com.consolo.auth.entities.Token;
import br.com.consolo.auth.entities.User;
import br.com.consolo.auth.repositories.TokenRepository;
import br.com.consolo.auth.repositories.UserRepository;
import br.com.consolo.auth.util.Util;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Environment environment;

    @Transactional
    public TokenDTO generateToken(UserDTO userDTO) {
        User user = userRepository.find(userDTO.getId());
        Token token = new Token();
        token.setUser(user);
        token.setCreatedAt(new Date());
        return new TokenDTO(insert(token));
    }

    @Transactional
    public void renewExpirationDate(String token) {
        Token t = tokenRepository.find(token);
        t.setExpirationDate(new Date());
        tokenRepository.merge(t);
    }

    @Transactional(readOnly = true)
    public TokenDTO getByToken(String token) {
    	try {
    		Token porToken = tokenRepository.find(token);
    		return new TokenDTO(porToken);
    	} catch (Exception e) {
    		return null;
    	}
    }

    @Transactional(readOnly = true)
    public TokenDTO getByUser(String idUser) {
        return new TokenDTO(tokenRepository.findByUser(idUser));
    }

    @Transactional
    private Token insert(Token token) {
        tokenRepository.persist(token);
        return token;
    }
    
    public boolean isTokenExpired(TokenDTO tokenDTO) {
    	
    	Date expirationDate = tokenDTO.getExpirationDate();
    	
    	if (expirationDate == null) return false;
    	
    	Date newDate = Util.addMinutesToData(expirationDate, environment);
    	
    	return newDate.compareTo(expirationDate) > 0;
    	
    }

}
