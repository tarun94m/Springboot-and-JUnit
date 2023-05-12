package com.springboot.cruddemo.rest;

import java.util.List;

import com.springboot.cruddemo.entity.Company;
import com.springboot.cruddemo.service.CompanyService;
import jakarta.websocket.server.PathParam;
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
import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyService companyService;


	// return list of all employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	// add mapping to get/employee/{employeeId}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found -" + employeeId);
		}
		return theEmployee;
	}

	// add mapping to add new employee post/employee
	@PostMapping("/employees/{companyId}")
	public Employee addEmployee(@RequestBody Employee theEmployee, @PathVariable int companyId) {
		Company company = companyService.findById(companyId);
		employeeService.save(theEmployee, company);
		return theEmployee;
	}
	
	//add mapping for put/employees or update an existing employee
	@PutMapping("/employees/{companyId}")
	public Employee updateEmployee(@RequestBody Employee theEmployee, @PathVariable int companyId) {
		Company company = companyService.findById(companyId);
		employeeService.save(theEmployee, company);
		return theEmployee;
	}
	
	//add mapping for delete an employee by employeeId
	@DeleteMapping("employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found-" +employeeId);
		}
		employeeService.deleteById(employeeId);
		return "deleted employee id is- " +employeeId;
		
	}
	

}
