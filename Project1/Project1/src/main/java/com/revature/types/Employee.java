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
		this.username = username.toLowerCase();
		this.password = password;
		manager = 0;
	}
	
	public Employee(String username, String password, int manager) {
		this.username = username.toLowerCase();
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
		if(test.getPassword() != null)
		{
			return password.equals(test.getPassword());
		}
		else
		{
			return false;
		}
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
		if(test.getUsername() != null)
		{
			return test.getUsername().equals(username.toLowerCase());
		}
		else
		{
			return false;
		}
	}
	
}
