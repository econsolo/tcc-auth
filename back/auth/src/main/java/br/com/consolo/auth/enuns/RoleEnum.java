package br.com.consolo.auth.enuns;

public enum RoleEnum {

	ADMIN("55615da08e514414a43c758ab5a0eed9"),
	USUAL("552e469a2fea4ab085ce04f4e6523bc0");
	
	private String id;
	
	RoleEnum(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
