package com.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.cruddemo.dao.CompanyRepository;
//import com.springboot.cruddemo.dao.EmployeeDAO;
import com.springboot.cruddemo.dao.EmployeeRepository;
import com.springboot.cruddemo.entity.Company;
import com.springboot.cruddemo.entity.Employee;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	
	public Company findById(int companyId) {

		Optional<Company> result = companyRepository.findById(companyId);
		Company company=null;
		if (result.isPresent()) {
			company= result.get();
		} else {
			throw new RuntimeException("Did not find the company id - " + companyId);
		}
		return company;
	}

	

	@Override
	public void deleteById(int companyId) {
		Company company = this.findById(companyId);
		List<Employee> employees = employeeService.fetchCompanyEmployees(company);
		employeeService.delete(employees);

		companyRepository.delete(company);
	}

	@Override
	public void save(Company company) {
		companyRepository.save(company);
	}

}
