package ca.signettours.customer.customerAddress;

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
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String street;

	@Column
	private String unit;

	@Column
	private String city;

	@Column
	private String province;

	@Column
	private String postal_code;

	@Column(length = 45)
	private String title;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public CustomerAddress() {

	}
	
	public CustomerAddress(Customer customer) {
		this.customer = customer;
	}
	
	public CustomerAddress(String street, String unit, String city, String province, String postal_code,
			String title, Customer customer) {
		this.street = street;
		this.unit = unit;
		this.city = city;
		this.province = province;
		this.postal_code = postal_code;
		this.title = title;
		this.customer = customer;
	}
	
	public CustomerAddress(Integer id, String street, String unit, String city, String province, String postal_code,
			String title, Customer customer) {
		this.id = id;
		this.street = street;
		this.unit = unit;
		this.city = city;
		this.province = province;
		this.postal_code = postal_code;
		this.title = title;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
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
		return unit + "  " + street + "  " + city + "  " + province + "  " +
				postal_code.toUpperCase() + "(" + title + ")";
	}
}
