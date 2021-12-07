package ca.signettours.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import ca.signettours.customer.customerAddress.CustomerAddress;
import ca.signettours.customer.customerEmail.CustomerEmail;
import ca.signettours.customer.customerMembership.CustomerMembership;
import ca.signettours.customer.customerPhone.CustomerPhone;
import ca.signettours.flight.Flight;
import ca.signettours.reservation.Reservation;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 45)
	private String first_name;

	@Column(length = 45)
	private String last_name;

	@Column
	private Character gender;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth_date;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerPhone> phones = new ArrayList<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerEmail> emails = new ArrayList<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerAddress> addresses = new ArrayList<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerMembership> memberships = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinTable(
			name = "reservation_customer",
			joinColumns = @JoinColumn(name = "customer_id"),
			inverseJoinColumns = @JoinColumn(name = "reservation_id")
			)
	private Set<Reservation> reservations = new HashSet<>();
	
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private List<Flight> flights = new ArrayList<>();
	
	public Customer() {
	}

	public Customer(Integer id) {
		this.id = id;
	}

	public Customer(String first_name) {
		this.first_name = first_name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}
	
	public List<CustomerPhone> getPhones() {
		return phones;
	}

	public void setPhones(List<CustomerPhone> phones) {
		this.phones = phones;
	}
	
	public void setPhones(Integer id, String number, String title) {
		this.phones.add(new CustomerPhone(id, number, title, this));
	}

	public void addPhone() {
		this.phones.add(new CustomerPhone(this));
	}
	
	public void addPhone(String number, String title) {
		this.phones.add(new CustomerPhone(number, title, this));
	}
	
	public List<CustomerEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<CustomerEmail> emails) {
		this.emails = emails;
	}
	
	public void setEmails(Integer id, String email, String title) {
		this.emails.add(new CustomerEmail(id, email, title, this));
	}
	
	public void addEmail() {
		this.emails.add(new CustomerEmail(this));
	}
	
	public void addEmail(String email, String title) {
		this.emails.add(new CustomerEmail(email, title, this));
	}

	public List<CustomerAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CustomerAddress> addresses) {
		this.addresses = addresses;
	}
	
	public void setAddresses(Integer id, String street, String unit, String city, String province, String postal_code, String title) {
		this.addresses.add(new CustomerAddress(id, street, unit, city, province, postal_code, title, this));
	}
	
	public void addAddress() {
		this.addresses.add(new CustomerAddress(this));
	}
	
	public void addAddress(String street, String unit, String city, String province, String postal_code, String title) {
		this.addresses.add(new CustomerAddress(street, unit, city, province, postal_code, title, this));
	}

	public List<CustomerMembership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<CustomerMembership> memberships) {
		this.memberships = memberships;
	}
	
	public void setMemberships(Integer id, String user_id, String username, String password, String company, String note) {
		this.memberships.add(new CustomerMembership(id, user_id, username, password, company, note, this));
	}
	
	public void addMembership() {
		this.memberships.add(new CustomerMembership(this));
	}
	
	public void addMembership(String user_id, String username, String password, String company, String note) {
		this.memberships.add(new CustomerMembership(user_id, username, password, company, note, this));
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
	
	@Override
	public String toString() {
		return first_name + ", " + last_name + "(" + gender + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	public void addFlight(Integer id, Reservation reservation, Customer customer) {
		this.flights.add(new Flight(id, reservation, customer));
	}
	
}
