package com.revature.types;

public class Employee {

	private String username;
	private String password;
	private  int manager;
	
	public Employee() {
		username = null;
		password = null;
		manager = 0;
	}
	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
		manager = 0;
	}
	
	public Employee(String username, String password, int manager) {
		this.username = username;
		this.password = password;
		this.manager = manager;
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

	public boolean checkPassword(Employee test) {
		return password.equals(test.getPassword());
	}
	
	public boolean isManager() {
		if(manager == 1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", manager=" + manager + "]";
	}
	
	public boolean equals(Employee test){
	return test.getUsername().equals(username);
	}
	
}
