package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.revature.types.Ticket;
import com.revature.util.ConnectionFactory;

public class TicketRepository {
	
	public Ticket getTicket() 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		Ticket tick = null;
		try{
			conn = ConnectionFactory.dbConnection();
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT * FROM tickets WHERE creator = 'testemployee'");
			results.next();
			
			tick = new Ticket(results.getString(0), results.getDouble(1), results.getString(2), results.getString(3));
			return tick;
		}catch(SQLException e) {
			e.getStackTrace();
		}finally {
			try {
				stmt.close();
				results.close();
			}catch(SQLException e) {
				e.getStackTrace();
			}
		}
		return tick;
	}
}
