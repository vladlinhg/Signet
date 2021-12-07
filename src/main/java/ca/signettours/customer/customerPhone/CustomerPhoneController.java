package ca.signettours.customer.customerPhone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.signettours.customer.Customer;
import ca.signettours.customer.CustomerRepository;

@Controller
public class CustomerPhoneController {
	
	@Autowired
	private CustomerPhoneRepository repo;
	
	@Autowired
	private CustomerRepository repo_c;
	
	@GetMapping("customers/edit/{id}/add_phone")
	public String addPhone(@PathVariable("id") Integer id, Model model) {
		Customer customer = repo_c.findById(id).get();
		model.addAttribute("customer", customer);
		customer.addPhone();
		
		repo_c.save(customer);
		
		return "redirect:/customers/edit/{id}";
	}
	@GetMapping("customers/edit/{id}/delete_phone/{phone_id}")
	public String deletePhone(@PathVariable("phone_id") Integer phone_id, Model model) {
		repo.deleteById(phone_id);
		
		return "redirect:/customers/edit/{id}";
	}

}
