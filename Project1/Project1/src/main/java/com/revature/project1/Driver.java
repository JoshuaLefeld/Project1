package com.revature.project1;

import com.revature.repository.TicketRepository;
import com.revature.types.Employee;
import com.revature.types.Ticket;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.repository.EmployeeRepository;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class Driver {

	public static void main(String[] args) {
		
		//Tracks the current employee who's logged in
		Employee currentuser = new Employee();
		EmployeeRepository employeerepo = new EmployeeRepository();
		TicketRepository ticketrepo = new TicketRepository();
		
		//Open Javalin with port 8000
		Javalin app = Javalin.create().start(8000);
		
		app.post("/login", (Context ctx) -> {
			if(currentuser.getUsername() == null)
			{
				Employee temp = ctx.bodyAsClass(Employee.class);
				Employee test = employeerepo.getEmployee(temp.getUsername());
				if((temp.equals(test)) && (temp.checkPassword(test))) 
				{
					currentuser.setUsername(test.getUsername());
					currentuser.setPassword(test.getPassword());
					currentuser.setManager(test.getManager());
					ctx.result("Login Successful");
					ctx.status(HttpStatus.ACCEPTED_202);
				}
				else 
				{
					ctx.result("Login Unsuccessful");
					ctx.status(HttpStatus.BAD_REQUEST_400);
				}
			}
			else
			{
				ctx.result("User has not logged out.");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}
		});
		
		app.post("/logout", (Context ctx) -> {
			if(currentuser.getUsername() != null)
			{
				currentuser.setUsername(null);
				currentuser.setPassword(null);
				currentuser.setManager(0);
				ctx.result("Logout Successful");
				ctx.status(HttpStatus.ACCEPTED_202);
			}
			else
			{
				ctx.result("There is no user currently logged in.");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}
		});
		
		app.post("/register", ctx -> {
			if(currentuser.getUsername() == null)
			{
				Employee temp = ctx.bodyAsClass(Employee.class);
				temp.setUsername(temp.getUsername().toLowerCase());
				if(employeerepo.registerEmployee(temp)) 
				{
					currentuser.setUsername(temp.getUsername());
					currentuser.setPassword(temp.getPassword());
					currentuser.setManager(temp.getManager());
					ctx.result("User has been sucessfully created.");
					ctx.status(HttpStatus.CREATED_201);
				}
				else 
				{
					ctx.result("Username is taken.");
					ctx.status(HttpStatus.UNAUTHORIZED_401);
				}
			}
			else
			{
				ctx.result("There is a user currently logged in.");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}
			
	    });
		
		app.post("/createticket", (Context ctx) -> {
			if(currentuser.getUsername() != null)
			{
				if(!currentuser.isManager())
					{
					Ticket temp = ctx.bodyAsClass(Ticket.class);
					temp.setCreator(temp.getCreator().toLowerCase());
					if(currentuser.getUsername().equals(temp.getCreator()))
						{
			
						if(ticketrepo.createTicket(temp))
							{
								temp.setId(ticketrepo.getTotalTickets());
								ctx.result("Ticket has been created!\n" + temp.toString());
								ctx.status(HttpStatus.CREATED_201);
							}
						else
							{
								ctx.result("Ticket could not be created!");
								ctx.status(HttpStatus.BAD_REQUEST_400);
							}
						}
					else 
						{
							ctx.result("Invalid ticket creator.");
							ctx.status(HttpStatus.FORBIDDEN_403);
						}
					}
				else
					{
						ctx.result("User is not an employee.");
						ctx.status(HttpStatus.FORBIDDEN_403);
					}
			}
			else
			{
				ctx.result("Not currently logged in.");
				ctx.status(HttpStatus.FORBIDDEN_403);
			}
		});
		
		app.get("/viewtickets", (Context ctx) -> {
			if(currentuser.getUsername() != null)
			{
				if(!currentuser.isManager())
					{
						Set<Ticket> ticket = ticketrepo.getTickets(currentuser.getUsername());
						ctx.result(ticket.toString());
					}
				if(currentuser.isManager())
				{
					Set<Ticket> ticket = ticketrepo.getPendingTickets();
					ctx.result(ticket.toString());
				}
			}
			else
			{
				ctx.result("Not currently logged in.");
				ctx.status(HttpStatus.FORBIDDEN_403);
			}
		});
		
		app.post("/updateticket", (Context ctx) -> {
			if(currentuser.getUsername() != null)
			{
				if(currentuser.isManager())
				{
					Ticket temp = ctx.bodyAsClass(Ticket.class);
					if(!temp.getStatus().equals("PENDING"))
					{
						if((!temp.getStatus().equals("APPROVE")) || (!temp.getStatus().equals("DENY")))
						{
							if(temp.getStatus().equals("APPROVE"))
							{
								if(ticketrepo.approveTicket(temp))
								{
									ctx.result("Ticket (" + temp.getId() + ") has been approved.");
									ctx.status(HttpStatus.CREATED_201);
								}
								else
								{
									ctx.result("Ticket could not be approved.");
									ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
								}
							}
							if(temp.getStatus().equals("DENY"))
							{
								if(ticketrepo.denyTicket(temp))
								{
									ctx.result("Ticket (" + temp.getId() + ") has been denied.");
									ctx.status(HttpStatus.CREATED_201);
								}
								else
								{
									ctx.result("Ticket could not be denied.");
									ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
								}
							}
						}
						else
						{
							ctx.result("Invalid status sent.");
							ctx.status(HttpStatus.BAD_REQUEST_400);
						}
					}
					else
					{
						ctx.result("Cannot change already approved/denied tickets.");
						ctx.status(HttpStatus.FORBIDDEN_403);
					}
				}
				else
				{
					ctx.result("User is not a manager.");
					ctx.status(HttpStatus.FORBIDDEN_403);
				}
			}
			else
			{
				ctx.result("Not currently logged in.");
				ctx.status(HttpStatus.FORBIDDEN_403);
			}
		});

	}
}
