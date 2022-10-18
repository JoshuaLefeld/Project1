package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.types.Ticket;
import com.revature.util.ConnectionFactory;

public class TicketRepository {
	
	public Set<Ticket> getTickets(String user) 
	{
		String SQLcommand = "SELECT * FROM tickets WHERE creator = ?";
		ResultSet results = null;
		Set<Ticket> tick = new HashSet<Ticket>();
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setString(1, user);
			results = stmt.executeQuery();
			while(results.next())
			{
			tick.add(new Ticket(results.getString(1), results.getDouble(2), results.getString(3), results.getString(4)));
			}
			return tick;
		}catch(SQLException e) {
			e.getStackTrace();
		}finally {
			try {
				results.close();
			}catch(SQLException e) {
				e.getStackTrace();
			}
		}
		return tick;
	}
}
