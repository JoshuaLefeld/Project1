package com.revature.types;

public class Ticket {

	private String creator;
	private double amount;
	private String description;
	private int id;
	private String status;

	public Ticket()
	{
		creator = null;
		amount = 0.0;
		description = null;
		status = "PENDING";
		id = 0;
	}
	
	public Ticket(String creator, double amount, String description) {
		this.creator = creator;
		this.amount = amount;
		this.description = description;
		id = -1;
		status = "PENDING";
	}
	
	public Ticket(String creator, double amount, String description, int id, String status) {
		this.creator = creator;
		this.amount = amount;
		this.description = description;
		this.id = id;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Ticket [creator=" + creator + ", amount=" + amount + ", description=" + description + ", id=" + id
				+ ", status=" + status + "]";
	}

	public boolean equals(Ticket test)
	{
		if(test.getId() > 0)
		{
			return creator.equals(test.getCreator());
		}
		else
		{
			return false;
		}
	}
	
}
