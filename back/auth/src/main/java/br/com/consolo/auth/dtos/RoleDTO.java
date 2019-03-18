package br.com.consolo.auth.dtos;

import br.com.consolo.auth.entities.Role;

public class RoleDTO extends BaseDTO {

    public RoleDTO() { }

    public RoleDTO(Role role) {
        if (role == null) return;

        id = role.getId();
        description = role.getDescription();
    }

    private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
