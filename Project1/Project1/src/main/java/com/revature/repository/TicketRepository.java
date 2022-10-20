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
	
	private int totaltickets;
	
	private int TotalTickets()
	{
		String SQLcommand = "SELECT COUNT(id) FROM tickets";
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			ResultSet results = stmt.executeQuery();
			if(results.next())
			{
				int count = results.getInt(1);
				return count;
			}
			else
			{
				return -1;
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return -1;
	}
	
	public int getTotalTickets() {
		return totaltickets;
	}
	
	public void setTotalTickets() {
		totaltickets = TotalTickets();
	}
	
	public TicketRepository() 
	{
		setTotalTickets();
		totaltickets = getTotalTickets();
	}
	
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
			tick.add(new Ticket(results.getString(1), results.getDouble(2), results.getString(3), results.getInt(4), results.getString(5)));
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
	
	public Set<Ticket> getPendingTickets() 
	{
		String SQLcommand = "SELECT * FROM tickets WHERE status = ?";
		ResultSet results = null;
		Set<Ticket> tick = new HashSet<Ticket>();
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setString(1, "PENDING");
			results = stmt.executeQuery();
			while(results.next())
			{
			tick.add(new Ticket(results.getString(1), results.getDouble(2), results.getString(3), results.getInt(4), results.getString(5)));
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
	
	public Ticket getTicket(int id) 
	{
		String SQLcommand = "SELECT * FROM tickets WHERE id = ?";
		ResultSet results = null;
		Ticket tick = null;
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			results.next();
			tick = new Ticket(results.getString(1), results.getDouble(2), results.getString(3), results.getInt(4), results.getString(5));
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
	
	public boolean approveTicket(Ticket updatedticket) 
	{
		String SQLcommand = "UPDATE tickets SET status = 'APPROVED' WHERE id = ?";
		Ticket check = new Ticket();
		try(Connection conn = ConnectionFactory.dbConnection()){
			check = getTicket(updatedticket.getId());
			if(check.getStatus().equals("PENDING"))
			{
				PreparedStatement stmt = conn.prepareStatement(SQLcommand);
				stmt.setInt(1, updatedticket.getId());
				stmt.executeUpdate();
				check = getTicket(updatedticket.getId());
				if(!check.getStatus().equals("PENDING"))
				{
					return true;
				}
				else
				{
					return false;
				}
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
	
	public boolean denyTicket(Ticket updatedticket) 
	{
		String SQLcommand = "UPDATE tickets SET status = 'APPROVED' WHERE id = ?";
		Ticket check = new Ticket();
		try(Connection conn = ConnectionFactory.dbConnection()){
			check = getTicket(updatedticket.getId());
			if(check.getStatus().equals("PENDING"))
			{
				PreparedStatement stmt = conn.prepareStatement(SQLcommand);
				stmt.setInt(1, updatedticket.getId());
				stmt.executeUpdate();
				check = getTicket(updatedticket.getId());
				if(!check.getStatus().equals("PENDING"))
				{
					return true;
				}
				else
				{
					return false;
				}
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

	public boolean createTicket(Ticket newticket)	
	{
		String SQLcommand = "INSERT INTO tickets VALUES (LOWER(?), ?, ?, ((SELECT count(id) FROM tickets)+1), 'PENDING')";
		Ticket check = new Ticket();
		try(Connection conn = ConnectionFactory.dbConnection()){
			PreparedStatement stmt = conn.prepareStatement(SQLcommand);
			stmt.setString(1, newticket.getCreator());
			stmt.setDouble(2, newticket.getAmount());
			stmt.setString(3, newticket.getDescription());
			stmt.executeUpdate();
			setTotalTickets();
			check = getTicket(totaltickets);
			if(check.getId() > 0 && newticket.equals(check))
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
