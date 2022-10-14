package com.revature.types;

public class Ticket {

	private String creator;
	private double amount;
	private String description;
	private String status;
	
	public Ticket(String creator, double amount, String description, String status) {
		super();
		this.creator = creator;
		this.amount = amount;
		this.description = description;
		this.status = status;
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
	
	@Override
	public String toString() {
		return "Ticket [creator=" + creator + ", amount=" + amount + ", description=" + description + ", status="
				+ status + "]";
	}

}
