package com.revature.types;

public class Employee {

	private String username;
	private String password;
	private  int isManager;
	
	public Employee(String username, String password, int isManager) {
		this.username = username;
		this.password = password;
		this.isManager = isManager;
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

	public int isManager() {
		return isManager;
	}

	public void setManager(int isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", isManager=" + isManager + "]";
	}
	
	
}
