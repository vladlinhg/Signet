package ca.signettours.flight;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.signettours.customer.Customer;
import ca.signettours.customer.CustomerRepository;
import ca.signettours.reservation.Reservation;
import ca.signettours.reservation.ReservationRepository;

@Controller
public class FlightController {
	
	@Autowired
	private FlightRepository repo;
	
	@Autowired
	private CustomerRepository repo_c;
	
	@Autowired
	private ReservationRepository repo_r;
	
	@GetMapping("/flights/new")
	public String showNewFlightForm(Model model) {
		
		model.addAttribute("flight", new Flight());
		
		return "flight_form";
	}
	
	@GetMapping("/flights/add/{reservation_id}/{customer_id}")
	public String addFlight(@PathVariable("reservation_id") Integer reservation_id, @PathVariable("customer_id") Integer customer_id, Model model) {
		Customer customer = repo_c.findById(customer_id).get();
		Reservation reservation = repo_r.findById(reservation_id).get();
		
		Flight f = new Flight(reservation, customer);
		
		model.addAttribute("customer", customer);
		customer.addFlight(f.getId(), reservation, customer);
		
		model.addAttribute("reservation", reservation);
		reservation.addFlight(f.getId(), reservation, customer);	
		
		model.addAttribute("flight", f);
		
		repo_c.save(customer);
		repo_r.save(reservation);
		
		return "flight_form";
	}
	
	@PostMapping("/flights/save")
	public String saveFlight(Flight flight, HttpServletRequest request, Model model, @Param("keyword") String keyword) {
		
		repo.save(flight);
		
		
		
		return "redirect:/reservations";
	}
}
