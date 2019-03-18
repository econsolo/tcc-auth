package br.com.consolo.auth.dtos;

public abstract class BaseDTO {

	protected String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
