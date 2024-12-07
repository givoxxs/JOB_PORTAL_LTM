package model.bean;

public class Account {
	private String id;
	private	String username;
	private String password;
	private String role;
	private String avatar_url;
	
	public Account(String id, String username, String role, String avatar_url) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.avatar_url = avatar_url;
	}
	
	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
