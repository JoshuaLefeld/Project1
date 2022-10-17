package com.revature.project1;

import com.revature.repository.TicketRepository;
import com.revature.types.Employee;
import com.revature.types.Ticket;
import com.revature.repository.EmployeeRepository;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class Driver {

	public static void main(String[] args) {
		
		//Open Javalin with port 8000
		Javalin app = Javalin.create().start(8000);
		
		app.get("/login", (Context ctx) -> {
			ctx.res().getWriter().write("Login place holder");
		});
		
		app.get("/register", (Context ctx) -> {
			ctx.res().getWriter().write("Register place holder");
		});
		
		app.get("/employeeloggedin", (Context ctx) -> {
			EmployeeRepository employeerepo = new EmployeeRepository();
			Employee tester = employeerepo.getEmployee();
			System.out.println(tester);
		});
		
		app.get("/managerloggedin", (Context ctx) -> {
			ctx.res().getWriter().write("Manager Logged in place holder");
		});
		
		app.get("/viewtickets", (Context ctx) -> {
			ctx.res().getWriter().write("View tickets placeholder");
		});
		
		app.get("/viewpasttickets", (Context ctx) -> {
			ctx.res().getWriter().write("View past tickets placeholder");
		});
	}
}
