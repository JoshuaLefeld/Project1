package com.revature.project1;

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
			ctx.res().getWriter().write("Employee Logged in place holder");
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
