package com.springboot.cruddemo.rest;
//package com.springboot.cruddemo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.entity.Company;
import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.CompanyService;
import com.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;

	// return list of all employees
	@GetMapping("/company")
	public List<Company> findAll() {
		return companyService.findAll();
	}

	// add mapping to get/employee/{employeeId}

	@GetMapping("/company/{companyId}")
	public Company getCompany(@PathVariable int companyId) {
		Company theCompany = companyService.findById(companyId);

		if (theCompany == null) {
			throw new RuntimeException("Company id not found -" + companyId);
		}
		return theCompany;
	}

	// add mapping to add new employee post/employee
	@PostMapping("/company")
	public Company addCompany(@RequestBody Company theCompany) {
		companyService.save(theCompany);
		return theCompany;
	}
	
	//add mapping for put/employees or update an existing employee
	@PutMapping("/company")
	public Company updateCompany(@RequestBody Company theCompany) {
		companyService.save(theCompany);
		return theCompany;
	}
	
	//add mapping for delete an employee by employeeId
	@DeleteMapping("company/{companyId}")
	public String deleteCompany(@PathVariable int companyId) {
		Company theCompany = companyService.findById(companyId);
		
		if (theCompany == null) {
			throw new RuntimeException("Company id not found-" +companyId);
		}
		companyService.deleteById(companyId);
		return "deleted Company id is- " +companyId;
		
	}

}
