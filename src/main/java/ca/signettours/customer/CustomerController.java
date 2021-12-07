package ca.signettours.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.signettours.flight.Flight;
import ca.signettours.reservation.Reservation;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository repo;
	
	
	@GetMapping("/customers/new")
	public String showNewCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		
		return "customer_form";
	}
	
	@GetMapping("/customers")
	public String listCustomers(Model model) {
		List<Customer> listCustomers = repo.findAll();
		
		model.addAttribute("listCustomers", listCustomers);
		return "customers";
	}
	
	@PostMapping("/customers/save")
	public String saveCustomer(Customer customer, HttpServletRequest request) {
		Boolean isNewCustomer = customer.getId() != null;
		
		if (isNewCustomer) {
			Customer c = repo.findById(Integer.valueOf(request.getParameter("id"))).get();
			Reservation[] reservations = c.getReservations().toArray(new Reservation[c.getReservations().size()]);
		
			if (reservations != null) {
				for (int i = 0; i < reservations.length; i++)
					customer.addReservation(reservations[i]);
			}	
		}
		
		String[] phoneIDs = request.getParameterValues("phoneID");
		String[] phoneNumbers = request.getParameterValues("phoneNumber");
		String[] phoneTitles = request.getParameterValues("phoneTitle");
		
		if (phoneIDs != null) {
			for (int i = 0; i < phoneIDs.length; i++)
				customer.setPhones(Integer.valueOf(phoneIDs[i]), phoneNumbers[i], phoneTitles[i]); 
		}
		
		String[] emailIDs = request.getParameterValues("emailID");
		String[] emailEmails = request.getParameterValues("emailEmail");
		String[] emailTitles = request.getParameterValues("emailTitle");
		
		if (emailIDs != null) {
			for (int i = 0; i < emailIDs.length; i++)
				customer.setEmails(Integer.valueOf(emailIDs[i]), emailEmails[i], emailTitles[i]);
		}
		
		String[] addressIDs = request.getParameterValues("addressID");
		String[] addressStreets = request.getParameterValues("addressStreet");
		String[] addressUnits = request.getParameterValues("addressUnit");
		String[] addressCitys = request.getParameterValues("addressCity");
		String[] addressProvinces = request.getParameterValues("addressProvince");
		String[] addressPostal_Codes = request.getParameterValues("addressPostal_Code");
		String[] addressTitles = request.getParameterValues("addressTitle");
		
		if (addressIDs != null) {
			for (int i = 0; i < addressIDs.length; i++)
				customer.setAddresses(Integer.valueOf(addressIDs[i]), addressStreets[i], addressUnits[i], 
						addressCitys[i], addressProvinces[i], addressPostal_Codes[i], addressTitles[i]); 
		}
		
		String[] membershipIDs = request.getParameterValues("membershipID");
		String[] membershipUser_ids = request.getParameterValues("membershipUser_id");
		String[] membershipUsernames = request.getParameterValues("membershipUsername");
		String[] membershipPasswords = request.getParameterValues("membershipPassword");
		String[] membershipCompanys = request.getParameterValues("membershipCompany");
		String[] membershipNotes = request.getParameterValues("membershipNote");
		
		if (membershipIDs != null) {
			for (int i = 0; i < membershipIDs.length; i++)
				customer.setMemberships(Integer.valueOf(membershipIDs[i]), membershipUser_ids[i], membershipUsernames[i], 
						membershipPasswords[i], membershipCompanys[i], membershipNotes[i]); 
		}
		
		repo.save(customer);
		
		if (isNewCustomer)
			return "redirect:/customers";
		return "customer_form";
	}
	
	@GetMapping("customers/edit/{id}")
	public String showEditCustomerForm(@PathVariable("id") Integer id, Model model) {
		Customer customer = repo.findById(id).get();
		model.addAttribute("customer", customer);
		
		return "customer_form";
	}
	
	@GetMapping("customers/info/{id}")
	public String showcustomerInfo(@PathVariable("id") Integer id, Model model) {
		Customer customer = repo.findById(id).get();
		model.addAttribute("customer", customer);
		
		List<Reservation> existingReservations = new ArrayList<>(customer.getReservations());
		model.addAttribute("existingReservations", existingReservations);
		
		List<Flight> existingFlights = new ArrayList<>(customer.getFlights());
		model.addAttribute("existingFlights", existingFlights);
		
		return "customer_info";
	}
	
	@GetMapping("customers/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Integer id) {
		repo.deleteById(id);
		
		return "redirect:/customers";
	}
}
