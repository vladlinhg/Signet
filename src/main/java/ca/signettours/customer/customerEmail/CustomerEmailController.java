package ca.signettours.customer.customerEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.signettours.customer.Customer;
import ca.signettours.customer.CustomerRepository;

@Controller
public class CustomerEmailController {
	
	@Autowired
	private CustomerEmailRepository repo;
	
	@Autowired
	private CustomerRepository repo_c;
	
	@GetMapping("customers/edit/{id}/add_email")
	public String addEmail(@PathVariable("id") Integer id, Model model) {
		Customer customer = repo_c.findById(id).get();
		model.addAttribute("customer", customer);
		customer.addEmail();
		
		repo_c.save(customer);
		
		return "redirect:/customers/edit/{id}";
	}
	@GetMapping("customers/edit/{id}/delete_email/{email_id}")
	public String deleteEmail(@PathVariable("email_id") Integer email_id, Model model) {
		repo.deleteById(email_id);
		
		return "redirect:/customers/edit/{id}";
	}
}
