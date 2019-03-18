package br.com.consolo.auth.dtos;

import java.util.Date;

import br.com.consolo.auth.entities.Token;

public class TokenDTO extends BaseDTO {

	public TokenDTO() {
	}

	public TokenDTO(Token t) {
		if (t == null)
			return;

		this.id = t.getId();
		this.userDTO = new UserDTO(t.getUser());
		this.createdAt = t.getCreatedAt();
		this.expirationDate = t.getExpirationDate();
	}

	private UserDTO userDTO;
	private Date createdAt;
	private Date expirationDate;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
