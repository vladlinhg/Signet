package ca.signettours.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("SELECT c FROM Customer c WHERE c.first_name LIKE %?1%"
			+ " OR c.last_name LIKE %?1%")
	public List<Customer> findAll(String keyword);

}
