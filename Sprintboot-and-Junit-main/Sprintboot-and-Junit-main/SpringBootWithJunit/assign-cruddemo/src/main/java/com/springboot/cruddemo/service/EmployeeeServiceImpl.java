package com.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import com.springboot.cruddemo.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.dao.EmployeeRepository;
import com.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	
	public Employee findById(int employeeId) {

		Optional<Employee> result = employeeRepository.findById(employeeId);
		Employee employee = null;
		if (result.isPresent()) {
			employee= result.get();
		} else {
			throw new RuntimeException("Did not find the employee id - " + employeeId);
		}
		return employee;
	}

	@Override
	public void save(Employee employee, Company company) {
		employee.setCompany(company);
		employeeRepository.save(employee);
	}

	@Override
	public void deleteById(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	@Override
	public boolean delete(List<Employee> employees) {
		employeeRepository.deleteAll(employees);
		return true;
	}

	public List<Employee> fetchCompanyEmployees(Company company) {
		return employeeRepository.findByCompany(company);
	}

}
