package com.revature.types;

public class Ticket {

	private String creator;
	private double amount;
	private String description;
	private String status;
	private int id;

	public Ticket(String creator, double amount, String description, String status, int id) {
		this.creator = creator;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Ticket [creator=" + creator + ", amount=" + amount + ", description=" + description + ", status="
				+ status + ", id=" + id + "]";
	}
	
}
