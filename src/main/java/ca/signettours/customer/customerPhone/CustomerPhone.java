package ca.signettours.customer.customerPhone;

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
@Table(name = "customer_phone")
public class CustomerPhone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String number;

	@Column(length = 45)
	private String title;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public CustomerPhone() {

	}
	
	public CustomerPhone(Customer customer) {
		this.customer = customer;
	}

	public CustomerPhone(Integer id, String number, String title, Customer customer) {
		this.id = id;
		this.number = number;
		this.title = title;
		this.customer = customer;
	}

	public CustomerPhone(String number, String title, Customer customer) {
		this.number = number;
		this.title = title;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		return number + "(" + title + ")";
	}

}
