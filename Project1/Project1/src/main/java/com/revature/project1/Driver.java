package com.revature.project1;

import com.revature.repository.TicketRepository;
import com.revature.types.Employee;
import com.revature.types.Ticket;

import java.util.HashSet;
import java.util.Set;

import com.revature.repository.EmployeeRepository;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class Driver {

	public static void main(String[] args) {
		
		//Open Javalin with port 8000
		Javalin app = Javalin.create().start(8000);
		Employee currentuser = null;
		
		app.post("/login", (Context ctx) -> {
			ctx.res().getWriter().write("Login place holder");
		});
		
		app.post("/new-user", ctx -> {
	           Employee recievedUser = ctx.bodyAsClass(Employee.class);
	           System.out.println(recievedUser);
	           ctx.status(201);
	    });
		
		app.get("/employeeloggedin", (Context ctx) -> {
			EmployeeRepository employeerepo = new EmployeeRepository();
			Employee tester = employeerepo.getEmployee("testemployee");
			ctx.result(tester.toString());
		});
		
		app.get("/managerloggedin", (Context ctx) -> {
			ctx.res().getWriter().write("Manager Logged in place holder");
		});
		
		app.get("/viewtickets", (Context ctx) -> {
			ctx.res().getWriter().write("View tickets(manager) placeholder");
		});
		
		app.get("/viewpasttickets", (Context ctx) -> {
			TicketRepository ticketrepo = new TicketRepository();
			Set<Ticket> tester = new HashSet<Ticket>(ticketrepo.getTickets("testemployee"));
			ctx.result(tester.toString());
		});
	}
}
