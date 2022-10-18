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
		String SQLcommand = "SELECT * FROM employees WHERE username = ?";
		ResultSet results = null;
		Employee emp = null;
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setString(1, user);
			results = stmt.executeQuery();
			results.next();
			emp = new Employee(results.getString(1), results.getString(2), results.getInt(3));
			return emp;
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
	
}
