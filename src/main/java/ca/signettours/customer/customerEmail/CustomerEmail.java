package ca.signettours.customer.customerEmail;

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
@Table
public class CustomerEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String email;
	
	@Column(length = 45)
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public CustomerEmail() {

	}

	public CustomerEmail(Customer customer) {
		this.customer = customer;
	}
	
	public CustomerEmail(String email, String title, Customer customer) {
		this.email = email;
		this.title = title;
		this.customer = customer;
	}
	
	public CustomerEmail(Integer id, String email, String title, Customer customer) {
		this.id = id;
		this.email = email;
		this.title = title;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return email + "(" + title + ")";
	}

}
