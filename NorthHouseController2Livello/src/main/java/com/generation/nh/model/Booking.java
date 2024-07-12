package com.generation.nh.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne()
	@JoinColumn(name="houseid", nullable=false)
	House house;     // hai il RIFERIMENTO al PADRE, o RIFERIMENTO A LATO 1

	@ManyToOne()
	@JoinColumn(name="userid", nullable=false)
	User user;     // hai il RIFERIMENTO al PADRE, o RIFERIMENTO A LATO 1
	
	LocalDate start;
	LocalDate end;

	double cost;

	
	
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate to) {
		this.end = to;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate date) {
		this.start = date;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	/**
	 * Un set con l'elenco dei giorni in cui la stanza è occupata da questa prenotazione
	 * @return
	 */
	public Set<LocalDate> getDays()
	{
		Set<LocalDate> res = new LinkedHashSet<LocalDate>();
		for
		(
			LocalDate d=start;					// d=start
			d.isBefore(end) || d.equals(end);	// d<=end
			d=d.plusDays(1)						// d++
		)
			res.add(d);
			
		return res;	
		
	}
	
	
	
	
	
	
}
