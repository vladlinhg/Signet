package ca.signettours.customer.customerAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.signettours.customer.Customer;
import ca.signettours.customer.CustomerRepository;

@Controller
public class CustomerAddressController {
	
	@Autowired
	private CustomerAddressRepository repo;
	
	@Autowired
	private CustomerRepository repo_c;
	
	@GetMapping("customers/edit/{id}/add_address")
	public String addAddress(@PathVariable("id") Integer id, Model model) {
		Customer customer = repo_c.findById(id).get();
		model.addAttribute("customer", customer);
		customer.addAddress();
		
		repo_c.save(customer);
		
		return "redirect:/customers/edit/{id}";
	}
	@GetMapping("customers/edit/{id}/delete_address/{address_id}")
	public String deleteAddress(@PathVariable("address_id") Integer address_id, Model model) {
		repo.deleteById(address_id);
		
		return "redirect:/customers/edit/{id}";
	}

}
