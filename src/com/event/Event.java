/*
 * Event object, store data of one event
 */

package com.event;

public class Event {

	/*
	 * attributes
	 */
	private int 	id;
	private String 	name;
	private MyDate 	date;
	private String 	description;
	private String 	place;
	private double 	amount;
	
	/*
	 * constructors
	 */
	public Event(){
		id = 0;
		name = description = place = null;
		date = null;
		amount = 0.0;
	}
	
	public Event(	int id,
					String name, 
					MyDate date,
					String description,
					String place,
					double amount){
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.place = place;
		this.amount = amount;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public MyDate getDate(){
		return this.date;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getPlace(){
		return this.place;
	}
	
	public double getAmount(){
		return this.amount;
	}
	
	public String toString(){
		String result = String.valueOf(this.id) + ";"
						+ this.name + ";"
						+ this.date.toString() + ";"
						+ this.description + ";"
						+ this.place + ";"
						+ String.valueOf(this.amount);
		return result;
	}
}
