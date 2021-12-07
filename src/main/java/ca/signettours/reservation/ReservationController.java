package ca.signettours.reservation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationRepository repo;
	
	@Autowired
	private CustomerRepository repo_c;
	
	@GetMapping("/reservations/new")
	public String showNewReservationForm(Model model) {
		
		model.addAttribute("reservation", new Reservation());
		
		return "reservation_form";
	}
	
	@GetMapping("/reservations")
	public String listReservations(Model model) {
		List<Reservation> listReservations = repo.findAll();
		model.addAttribute("listReservations", listReservations);
		
		return "reservations";
	}
	
	@PostMapping("/reservations/save")
	public String saveReservation(Reservation reservation, HttpServletRequest request, Model model, @Param("keyword") String keyword) {
		Boolean isNewReservation = reservation.getId() != null;
		Boolean isSearchPressed = false; 
		
		if(request.getParameter("search_button") != null)
		    isSearchPressed = request.getParameter("search_button").equals("Search"); 
		
		if (isNewReservation) {
			Reservation r = repo.findById(Integer.valueOf(request.getParameter("id"))).get();
			Customer[] customers = r.getCustomers().toArray(new Customer[r.getCustomers().size()]);
		
			if (customers != null) {
				for (int i = 0; i < customers.length; i++)
					reservation.addCustomer(customers[i]);
			}	
		}

		repo.save(reservation);
			
		List<Customer> existingCustomers = new ArrayList<>(reservation.getCustomers());
		
		List<Customer> listCustomers = repo_c.findAll();
		
		if (isSearchPressed)
			listCustomers = repo_c.findAll(keyword);
		
		listCustomers.removeAll(new HashSet<Customer>(existingCustomers));
		
		model.addAttribute("existingCustomers", existingCustomers);
		model.addAttribute("listCustomers", listCustomers);
			
		if (isSearchPressed)
			return "reservation_form";
		
		if (isNewReservation) 
			return "redirect:/reservations";
		
		return "reservation_form";
	}
	
	@GetMapping("reservations/edit/{id}")
	public String showEditReservationForm(@PathVariable("id") Integer id, Model model) {
		Reservation reservation = repo.findById(id).get();
		model.addAttribute("reservation", reservation);
		
		List<Customer> listCustomers = repo_c.findAll();
		
		List<Customer> existingCustomers = new ArrayList<>(reservation.getCustomers());
		
		listCustomers.removeAll(new HashSet<Customer>(existingCustomers));
		
		model.addAttribute("existingCustomers", existingCustomers);
		model.addAttribute("listCustomers", listCustomers);
		
		return "reservation_form";
	}
	
	@GetMapping("reservations/info/{id}")
	public String showReservationInfo(@PathVariable("id") Integer id, Model model) {
		Reservation reservation = repo.findById(id).get();
		model.addAttribute("reservation", reservation);
		
		List<Customer> existingCustomers = new ArrayList<>(reservation.getCustomers());
		model.addAttribute("existingCustomers", existingCustomers);
		
		return "reservation_info";
	}
	
	@GetMapping("reservations/edit/{id}/add_customer/{customer_id}")
	public String editReservationAddCustomer(@PathVariable("id") Integer id, @PathVariable("customer_id") Integer customer_id) {
		Reservation reservation = repo.findById(id).get();
		
		Customer customer = repo_c.findById(customer_id).get();
		reservation.addCustomer(customer);
		
		repo.save(reservation);
		
		return "redirect:/reservations/edit/{id}";
	}
	
	@GetMapping("reservations/edit/{id}/delete_customer/{customer_id}")
	public String editReservationDeleteCustomer(@PathVariable("id") Integer id, @PathVariable("customer_id") Integer customer_id) {
		Reservation reservation = repo.findById(id).get();
		
		Customer customer = repo_c.findById(customer_id).get();
		reservation.removeCustomer(customer);
		
		repo.save(reservation);
		
		return "redirect:/reservations/edit/{id}";
	}
	
	@GetMapping("reservations/delete/{id}")
	public String deleteReservation(@PathVariable("id") Integer id) {
		repo.deleteById(id);
		
		return "redirect:/reservations";
	}

}
