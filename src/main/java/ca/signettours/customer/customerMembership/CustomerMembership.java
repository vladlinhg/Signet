package ca.signettours.customer.customerMembership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ca.signettours.customer.Customer;

@Entity
@Table(name = "customer_membership")
public class CustomerMembership {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String user_id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String company;

	@Column(length = 45)
	private String note;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public CustomerMembership() {
	
	}
	
	public CustomerMembership(Customer customer) {
		this.customer = customer;
	}
	
	public CustomerMembership(String user_id, String username, String password, String company, String note,
			Customer customer) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.company = company;
		this.note = note;
		this.customer = customer;
	}
	
	public CustomerMembership(Integer id, String user_id, String username, String password, String company, String note,
			Customer customer) {
		this.id = id;
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.company = company;
		this.note = note;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return company;
	}
}
