package ca.signettours.customer.customerMembership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.signettours.customer.Customer;
import ca.signettours.customer.CustomerRepository;

@Controller
public class CustomerMembershipController {
	
	@Autowired
	private CustomerMembershipRepository repo;
	
	@Autowired
	private CustomerRepository repo_c;
	
	@GetMapping("customers/edit/{id}/add_membership")
	public String addMembership(@PathVariable("id") Integer id, Model model) {
		Customer customer = repo_c.findById(id).get();
		model.addAttribute("customer", customer);
		customer.addMembership();
		
		repo_c.save(customer);
		
		return "redirect:/customers/edit/{id}";
	}
	@GetMapping("customers/edit/{id}/delete_membership/{membership_id}")
	public String deleteMembership(@PathVariable("membership_id") Integer membership_id, Model model) {
		repo.deleteById(membership_id);
		
		return "redirect:/customers/edit/{id}";
	}

}
