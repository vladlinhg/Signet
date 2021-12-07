package ca.signettours;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DBController {
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

}
