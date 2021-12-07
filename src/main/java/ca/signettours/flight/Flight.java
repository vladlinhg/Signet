package ca.signettours.flight;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import ca.signettours.customer.Customer;
import ca.signettours.reservation.Reservation;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String flight_number;
	
	@Column
	private String airline;
	
	@Column
	private String seat_plan;
	
	@Column
	private String cabin_class;
	
	@Column
	private String meal;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime time_departure;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime time_arrival;
	
	@Column
	private String airport_departure;
	
	@Column
	private String airport_arrival;
	
	@Column
	private String additional_special_request;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Flight() {
	}
	
	public Flight(Reservation reservation, Customer customer) {
		this.reservation = reservation;
		this.customer = customer;
	}
	
	public Flight(Integer id, Reservation reservation, Customer customer) {
		this.id = id;
		this.reservation = reservation;
		this.customer = customer;
	}
	
	public Flight(Integer id, String flight_number, String airline, String seat_plan, String meal,
			LocalDateTime time_departure, LocalDateTime time_arrival, String airport_departure, String airport_arrival,
			String additional_special_request, Reservation reservation, Customer customer) {
		this.id = id;
		this.flight_number = flight_number;
		this.airline = airline;
		this.seat_plan = seat_plan;
		this.meal = meal;
		this.time_departure = time_departure;
		this.time_arrival = time_arrival;
		this.airport_departure = airport_departure;
		this.airport_arrival = airport_arrival;
		this.additional_special_request = additional_special_request;
		this.reservation = reservation;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getSeat_plan() {
		return seat_plan;
	}

	public void setSeat_plan(String seat_plan) {
		this.seat_plan = seat_plan;
	}

	public String getCabin_class() {
		return cabin_class;
	}

	public void setCabin_class(String cabin_class) {
		this.cabin_class = cabin_class;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public LocalDateTime getTime_departure() {
		return time_departure;
	}

	public void setTime_departure(LocalDateTime time_departure) {
		this.time_departure = time_departure;
	}

	public LocalDateTime getTime_arrival() {
		return time_arrival;
	}

	public void setTime_arrival(LocalDateTime time_arrival) {
		this.time_arrival = time_arrival;
	}

	public String getAirport_departure() {
		return airport_departure;
	}

	public void setAirport_departure(String airport_departure) {
		this.airport_departure = airport_departure;
	}

	public String getAirport_arrival() {
		return airport_arrival;
	}

	public void setAirport_arrival(String airport_arrival) {
		this.airport_arrival = airport_arrival;
	}

	public String getAdditional_special_request() {
		return additional_special_request;
	}

	public void setAdditional_special_request(String additional_special_request) {
		this.additional_special_request = additional_special_request;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return flight_number;
	}

}
