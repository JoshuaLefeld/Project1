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
		
		//Open Javalin with port 8000
		Javalin app = Javalin.create().start(8000);
		
		app.post("/login", (Context ctx) -> {
			Employee temp = ctx.bodyAsClass(Employee.class);
			EmployeeRepository employeerepo = new EmployeeRepository();
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
		});
		
		app.post("/register", ctx -> {
	           Employee recievedUser = ctx.bodyAsClass(Employee.class);
	           System.out.println(recievedUser);
	           ctx.status(HttpStatus.CREATED_201);
	    });
		
		app.get("/employeeloggedin", (Context ctx) -> {
			EmployeeRepository employeerepo = new EmployeeRepository();
			Employee tester = employeerepo.getEmployee("testemployee");
			ctx.result(tester.toString());
			ctx.status(HttpStatus.UNAUTHORIZED_401);
		});
		
		app.get("/managerloggedin", (Context ctx) -> {
			ctx.res().getWriter().write("Manager Logged in place holder");
		});
		
		app.get("/viewtickets", (Context ctx) -> {
			ctx.res().getWriter().write("View tickets(manager) placeholder");
		});
		
		app.get("/approveticket", (Context ctx) -> {
			ctx.res().getWriter().write("View tickets(manager) placeholder");
		});
		
		app.get("/denyticket", (Context ctx) -> {
			ctx.res().getWriter().write("View tickets(manager) placeholder");
		});
		
		app.get("/viewpasttickets", (Context ctx) -> {
			TicketRepository ticketrepo = new TicketRepository();
			Set<Ticket> tester = new HashSet<Ticket>(ticketrepo.getTickets("testemployee"));
			ctx.result(tester.toString());
		});
	}
}
