package com.springboot.cruddemo.service;

import java.util.List;

import com.springboot.cruddemo.entity.Company;
import com.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee, Company company);
	
	public void deleteById(int theId);

	public List<Employee> fetchCompanyEmployees(Company company);

	public boolean delete(List<Employee> employees);
}
