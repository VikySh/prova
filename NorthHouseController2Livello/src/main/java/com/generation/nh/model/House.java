package com.generation.nh.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * A house for rent for a night.
 * @author Ferdinando
 *
 */
@Entity
public class House
{
	
	// variabili statiche
	private final static String[] CITIES = 
		{"Bellagio", "Bergamo", "Milano", "Montevecchia", "Trezzo",
				"Varenna"
		};

	private final static String[] ADDRESSES = 
		{
			"Via Roma",
			"Via Milano",
			"Via dei Campi",
			"Via delle Vie",
			"Via Vai",
			"Via col Vento"
		};
	
	private static String randomCity()
	{
		return CITIES[(int)(Math.random()*CITIES.length)];
	}
	
	private static String randomAddress()
	{
		return ADDRESSES[(int)(Math.random()*ADDRESSES.length)];
	}

	private static int randomPrice()
	{
		return (int)(Math.random()*200)+50;
	}
	
	public static String randomParagraphs()
	{
		int n = 1;
		String paragraph = "Welcome to where time stands still, no one leaves no one ever will. Sanitarium, leave me be, sanitarium, just leave me alone. Machine gun took my eyes, took my ears, took my tongue. This torn in my side is from the tree i Planted";
		
		String res = "";
		for(int i=0;i<n;i++)
			res+=paragraph;
		
		return res;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	String city;
	String address;
	String description;
	String image;
	int floor;
	int price;
	
	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Review> reviews;
	
	
	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Booking> bookings;
	

	public House(int id)
	{
		this.id = id;
		this.name = "House "+id;
		this.city = randomCity();
		this.address = randomAddress();
		this.image = this.city.toLowerCase()+".jpeg";
		this.price = randomPrice();
		this.floor = 1;
		this.description = randomParagraphs();
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public House() {}
	
	public House(int id, String city, String address, String description, String image, int floor, int price) 
	{
		this.id = id;
		this.city = city;
		this.address = address;
		this.description = description;
		this.image = image;
		this.floor = floor;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	@Override
	public String toString() {
		return "House [id=" + id + ", city=" + city + ", address=" + address + ", description=" + description
				+ ", image=" + image + ", floor=" + floor + ", price=" + price + "]";
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	/**
	 * Restituisce true se questa casa è libera
	 * nelle date specificate dalla prenotazione
	 * @return
	 */
	public boolean isFreeFor(Booking newBooking)
	{
		Set<LocalDate> occupied = new HashSet<LocalDate>();		
		for(Booking b:bookings)
			occupied.addAll(b.getDays());
		// mantieni solo le date di occupied
		// che siano anche in newBooking.getDays()
		// vale a dire nelle date della nuova prenotazione
		occupied.retainAll(newBooking.getDays()); // INTERSERCA occupied con newBooking.getDays()
		
		// quand'è che sono libero per quella prenotazione?
		// quando le date della prenotazione non hanno nessuna data
		// in comune con quelle già occupate
		return occupied.size()==0;
	}
	

	
}
