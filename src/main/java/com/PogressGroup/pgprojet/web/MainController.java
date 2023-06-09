package com.PogressGroup.pgprojet.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.PogressGroup.pgprojet.model.Employee;
import com.PogressGroup.pgprojet.service.EmployeeService;



@Controller
public class MainController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@Autowired
	private EmployeeService employeeService;
	
		@GetMapping("/home")
		public String viewHomePage(Model model) {
			return findPaginated(1, "firstName", "asc", model);		
		}
		
		
		@GetMapping("/home/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
			int pageSize = 5;
			
			Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
			List<Employee> listEmployees = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			
			model.addAttribute("listEmployees", listEmployees);
			return "home";
		}
	

}
