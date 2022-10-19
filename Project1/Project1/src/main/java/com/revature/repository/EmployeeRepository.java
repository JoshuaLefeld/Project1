package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.types.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeRepository {
	
	public Employee getEmployee(String user)
	{
		String SQLcommand = "SELECT * FROM employees WHERE username = LOWER(?)";
		ResultSet results = null;
		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setString(1, user);
			results = stmt.executeQuery();
			if(results.next())
			{
				emp = new Employee(results.getString(1), results.getString(2), results.getInt(3));
				return emp;
			}
			else
			{
				return emp;
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}finally {
			try {
				results.close();
			}catch(SQLException e) {
				e.getStackTrace();
			}
		}
		return emp;
	}
	
	public boolean registerEmployee(Employee newuser)
	{
		String SQLcommand = "INSERT INTO employees VALUES (LOWER(?), ?, ?)";
		Employee check = new Employee();
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setString(1, newuser.getUsername());
			stmt.setString(2, newuser.getPassword());
			stmt.setInt(3, newuser.getManager());
			stmt.executeUpdate();
			check = getEmployee(newuser.getUsername());
			if(check.getUsername() != null && newuser.equals(check))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return false;
	}
	
}
