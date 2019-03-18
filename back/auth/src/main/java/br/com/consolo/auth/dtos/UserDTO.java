package br.com.consolo.auth.dtos;

import br.com.consolo.auth.entities.User;

public class UserDTO extends BaseDTO {

	public UserDTO(User user) {
		id = user.getId();
		email = user.getEmail();
		roleDTO = new RoleDTO(user.getRole());
	}

	private String email;
	private String password;
	private RoleDTO roleDTO;

	public UserDTO() { }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

}
