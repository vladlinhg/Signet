package ca.signettours;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import ca.signettours.customer.Customer;
import ca.signettours.reservation.Reservation;
import ca.signettours.reservation.ReservationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReservationRepositoryTests {
	
	@Autowired
	private ReservationRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCustomers() {
		Customer moyed = new Customer("Moyed");
		Customer husky = new Customer("Husky");
		Customer malamute = new Customer("Malamute");
		
		entityManager.persist(moyed);
		entityManager.persist(husky);
		entityManager.persist(malamute);
	}
	
	@Test
	public void testCreateNewReservationWithOneCustomer() {
		Customer malamute = entityManager.find(Customer.class, 17);
		Reservation reservation = new Reservation(1024, LocalDate.now());
		reservation.addCustomer(malamute);
		
		repo.save(reservation);
	}
	
	@Test
	public void testCreateNewReservationWithTwoCustomer() {
		Customer moyed = entityManager.find(Customer.class, 17);
		Customer husky = entityManager.find(Customer.class, 18);
		
		Reservation reservation = new Reservation(1025, LocalDate.now());
		reservation.addCustomer(moyed);
		reservation.addCustomer(husky);
		
		repo.save(reservation);
	}
	
	@Test
	public void testAssignCustomerToExistingReservation() {
		Reservation reservation = repo.findById(1).get();
		Customer moyed = entityManager.find(Customer.class, 19);
		reservation.addCustomer(moyed);
	}
	
	@Test
	public void testRemoveCustomerFromExistingReservation() {
		Reservation reservation = repo.findById(3).get();
		Customer customer = new Customer(7);
		reservation.removeCustomer(customer);
	}
	
	@Test
	public void testRemoveReservation() {
		repo.deleteById(1);
	}

}
